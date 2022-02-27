package parkingLotManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import redis.clients.jedis.Jedis;

/**
 * @author Senthil kumar.V
 * @version 1.0
 * @since  2022-02-27
 */
public class ParkingLotImpl implements ParkingLot{
	static int carparkingrate =50; // used to store parking rate per hour for car
	static int bikeparkingrate =10; // used to store parking rate per hour for bike
	static int slots=10;// number of space available per each slots
	static int parkingcount=0; //intialize the parkingcount to zero to store the value in parkinghistory
	static HashMap<String, String> price = new HashMap<String, String>(); // used to find price while check-out(vehicle-number,entry-time)
	static HashMap<String, String> freearray = new HashMap<String, String>(); // used to find and freeup space from lots once check-out happens	(vehicle-number,lot-name)
	static HashMap<String, String> vehicletype = new HashMap<String, String>(); // used to get the vehicle type(vehicle-number,(vehicle-number,vehicle-type)
	static SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T'hh:mm"); // Used to format date(type-1)
	static SimpleDateFormat formatter1= new SimpleDateFormat("dd/MM/yyy"); // Used to format date(type-2)
	static SimpleDateFormat formatter2= new SimpleDateFormat("hh:mm a"); // Used to format date(type-3)
	static String[][] parkinglots = new String[4][10]; // used to store total parking lots
	static String[][] parkingHistory = new String[100][5];// used to store history of the cars
	static String[][] temparray = new String[20][5];//used to temporarily store parkingHistory
	Jedis j = RedisConnector.getJ();//get the instance of redis connector

	/**
	 * This function is used to check in the parkinglots array for the
	 * particular slot(A or B or C or D or E) that user have asked for,
	 * if there is space for the vehicle to get parked
	 * 
	 * @param lotvalue
	 * @return - boolean true if the space is available in parking lot, boolean false if there is no space available in parking lot
	 */
	@Override
	public boolean ciCheck(int lotvalue) {
		getParkingLotCache();
		for(int i=0;i<slots;i++)
		{
			if(parkinglots[lotvalue][i]==null)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This function is used to allocate the slot for the vehicle after the ciCheck method
	 * add the value of the vehicle number and entry time to the pricecache
	 * add the value of the vehicle number and lotname to the freearraypricecache
	 * add the value of the vehicle type and entry time to the vehicletypecache
	 * 
	 * @param lotvalue - value of the parking lot (A,B,C,D,E) in an integer format
	 * @param vnumber - vehicle number of the vehicle to be parked
	 * @param vtype - type of the vehicle
	 * @param entrytime - entry time of the vehicle during check-in
	 * @param lotname - value of the parking lot (A,B,C,D,E)
	 * @return the success message if the vehicle checked-in
	 * @throws ParseException
	 */
	@Override
	public String allocate(int lotvalue, String vnumber, String vtype, String entrytime, char lotname) throws ParseException
	{
		Date date = formatter.parse(entrytime);
		//price.put(vnumber, entrytime);
		j.hset("pricecache", vnumber,entrytime);
		//freearray.put(vnumber, Character.toString(lotname));
		j.hset("freearraycache", vnumber,Character.toString(lotname));
		//vehicletype.put(vnumber, vtype);
		j.hset("vehicletypecache", vnumber,vtype);
		getParkingLotCache();
		for(int i=0;i<slots;i++)
		{
			if(parkinglots[lotvalue][i]==null)
			{
				parkinglots[lotvalue][i] = vnumber;
				j.hset ("parkinglotscache",Integer.toString(lotvalue)+Integer.toString(i), vnumber);
				return("*The Vehicle is parked at "+lotname+(i+1));
			}
		}
		return null;
	}

	/**
	 * This function is used to check in the pricecache array if the vehicle is already checked-in or not
	 * 
	 * @param vnum - vehicle number of the vehicle
	 * @return boolean true if the vehicle is available in pricecache, boolean false if the vehicle is not available in pricecache
	 */
	@Override
	public boolean coCheck(String vnum)
	{
		//return price.containsKey(vnum);
		return j.hexists("pricecache", vnum);
	}

	/**
	 * This function is used to calculate the price for the total duration vehicle has been parked in the parking
	 * it then populates the parkinghistorycache with the checked-out car details
	 * 
	 * @param vnum - vehicle number of the vehicle
	 * @param exittime - exit time of the vehicle during check-out
	 * @return - success messsge along with vehcile number, lotname, hours, amount to be paid
	 * @throws ParseException
	 */
	@Override
	public String calculatePrice(String vnum, String exittime) throws ParseException {
		Date entrytime= formatter.parse((j.hgetAll("pricecache").get(vnum)));
		Date exittime1 = formatter.parse(exittime);
		long Hours =((exittime1.getTime()-entrytime.getTime())/(3600000));
		float mins =((exittime1.getTime()-entrytime.getTime())/(60000))-(Hours*60);
		String vtype = j.hgetAll("vehicletypecache").get(vnum);
		String lot = getslot(vnum);
		//
		//		parkingHistory[parkingcount][0] =vnum;
		//		parkingHistory[parkingcount][1] =lot;
		//		parkingHistory[parkingcount][2] =formatter1.format(entrytime);
		//		parkingHistory[parkingcount][3] =formatter2.format(entrytime);
		//		parkingHistory[parkingcount][4] =formatter2.format(exittime1);

		j.hset("parkinghistorycache", Integer.toString(parkingcount)+"0",vnum);
		j.hset("parkinghistorycache", Integer.toString(parkingcount)+"1",lot);
		j.hset("parkinghistorycache", Integer.toString(parkingcount)+"2",formatter1.format(entrytime));
		j.hset("parkinghistorycache", Integer.toString(parkingcount)+"3",formatter2.format(entrytime));
		j.hset("parkinghistorycache", Integer.toString(parkingcount)+"4",formatter2.format(exittime1));


		parkingcount++;

		if(vtype.equals("CAR"))
		{
			int pricetobepaid = (int) ((Hours)*(carparkingrate) + ((mins/60) * (carparkingrate)));
			return ("Vehicle ("+vnum+") is available in "+lot+" \nThe parking duration of :"+Hours+" hours:"+(int)mins+" mins costs Rs.:"+pricetobepaid);
		}
		else
		{
			int pricetobepaid = (int) ((Hours)*(bikeparkingrate) + ((mins/60) * (bikeparkingrate)));
			return ("Vehicle ("+vnum+") is available in "+lot+" \nThe parking duration of :"+Hours+" hours:"+(int)mins+" mins costs Rs.:"+pricetobepaid);
		}
	}

	/**
	 * This function is used to remove the slot for the vehicle after the Check-out is complete
	 * removes the value of the vehicle number and entry time to the pricecache
	 * removes the value of the vehicle number and lotname to the freearraypricecache
	 * removes the value of the vehicle type and entry time to the vehicletypecache
	 * 
	 * @param vnumber - vehicle number of the vehicle
	 */
	@Override
	public void freeSpace(String vnumber) {
		//price.remove(vnumber);
		j.hdel("pricecache", vnumber);
		getParkingLotCache();
		int lot = ((j.hgetAll("freearraycache").get(vnumber).charAt(0))-65);
		for(int i=0;i<slots;i++)
		{
			if(((parkinglots[lot][i])!=null)&&((parkinglots[lot][i]).equals(vnumber)))
			{
				parkinglots[lot][i] = null;
				j.hdel("parkinglotscache", Integer.toString(lot)+Integer.toString(i));
				//freearray.remove(vnumber);
				j.hdel("freearraycache", vnumber);
			}
		}
	}

	/**
	 * This function is used to check in the pricecache array if the vehicle is already checked-in or not
	 * 
	 * @param vnumber - vehicle number of the vehicle
	 * @return boolean true if the vehicle is available in pricecache, boolean false if the vehicle is not available in pricecache
	 */
	@Override
	public boolean fmvCheck(String vnum)
	{
		//return price.containsKey(vnum);
		return j.hexists("pricecache", vnum);
	}

	/**
	 * This method is used to find the slotname and number at which the vehicle is parked
	 * 
	 * @param vnum - vehicle number of the vehicle
	 * @return the slotname along with the slotnumber
	 */
	@Override
	public String getslot(String vnum)
	{
		getParkingLotCache();
		//Character lotname = freearray.get(vnum);
		Character lotname =j.hgetAll("freearraycache").get(vnum).charAt(0);
		int lotvalue = (int)(lotname)-65;
		for(int i=0;i<slots;i++)
		{
			if(((parkinglots[lotvalue][i])!=null)&&((parkinglots[lotvalue][i]).equals(vnum)))
			{
				return Character.toString(lotname)+(i+1);
			}
		}
		return null;
	}

	/**
	 * This method is used to get the check-in time of the vehicle from the pricecache
	 * 
	 * @param vnumber - vehicle number of the vehicle
	 * @return time - check-in time
	 * @throws ParseException
	 */
	@Override
	public Date getEntryTime(String vnumber) throws ParseException {
		Date time = formatter.parse((j.hgetAll("pricecache").get(vnumber)));
		return time;
	}

	/**
	 * This method is used to check in the freeararycache and populate the remaining available slot details in all the slots
	 * 
	 * @return availableSlots - it contains the total available slots details in all the slots
	 */
	@Override
	public String[][] showAvailableSlots() {
		int acount=0;
		int bcount=0;
		int ccount=0;
		int dcount=0;
		//Collection<Character> value = freearray.values();
		Collection<String> value =  j.hvals("freearraycache");
		String[][] availableSlots = new String[4][4];
		for(String x:value)
		{
			if(x.equals("A"))
			{
				acount++;
			}
			if(x.equals("B"))
			{
				bcount++;
			}
			if(x.equals("C"))
			{
				ccount++;
			}
			if(x.equals("D"))
			{
				dcount++;
			}
		}
		availableSlots[0][0]="A";
		availableSlots[1][0]="B";
		availableSlots[2][0]="C";
		availableSlots[3][0]="D";

		availableSlots[0][1]="10";
		availableSlots[1][1]="10";
		availableSlots[2][1]="10";
		availableSlots[3][1]="10";

		availableSlots[0][2]=Integer.toString(acount);
		availableSlots[1][2]=Integer.toString(bcount);
		availableSlots[2][2]=Integer.toString(ccount);
		availableSlots[3][2]=Integer.toString(dcount);

		availableSlots[0][3]=Integer.toString(10-acount);
		availableSlots[1][3]=Integer.toString(10-bcount);
		availableSlots[2][3]=Integer.toString(10-ccount);
		availableSlots[3][3]=Integer.toString(10-dcount);


		return availableSlots;
	}

	/**
	 * This method is used to get the lot value from the stored redis cache (parkinglotscache)
	 */
	@Override
	public void getParkingLotCache()
	{
		Map<String, String> temp = new HashMap<String, String>();
		temp = j.hgetAll("parkinglotscache");

		for(int i=0;i<parkinglots.length;i++)
		{
			for(int j=0;j<parkinglots[0].length;j++)
			{
				parkinglots[i][j] = temp.get(Integer.toString(i)+Integer.toString(j));
			}
		}
	}

	/**
	 *  This method is used to get the parkinghistory the stored redis cache (parkinghistorycache)
	 */
	@Override
	public void getParkingHistoryCache() {
		Map<String, String> temp = new HashMap<String, String>();
		temp = j.hgetAll("parkinghistorycache");

		for(int i=0;i<parkingHistory.length;i++)
		{
			for(int j=0;j<parkingHistory[0].length;j++)
			{
				parkingHistory[i][j] = temp.get(Integer.toString(i)+Integer.toString(j));
			}
		}
	}

	/**
	 * This method is used to iterate through the parkignhistory cache and find the last five parking history of the vehicle
	 * 
	 * @param vnumber - vehicle number of the vehicle
	 * @return - finalarray with the last five parking history of the vehicle
	 */
	@Override
	public String[][] getParkingHistory(String vnumber) {
		String[][] temparray = new String[20][5];
		String[][] finalarray; //to send the array to jsp containing the required count
		int i=0;
		int j=0;
		int k=0;
		int count = 0;
		getParkingHistoryCache();
		for(i=0;i<parkingHistory.length;i++)
		{
			if(parkingHistory[i][0]==null)
			{
				continue;
			}
			else if(parkingHistory[i][0].equals(vnumber))
			{
				temparray[j][0] = parkingHistory[i][0];
				temparray[j][1] = parkingHistory[i][1];
				temparray[j][2] = parkingHistory[i][2];
				temparray[j][3] = parkingHistory[i][3];
				temparray[j][4] = parkingHistory[i][4];
				count++;
				j++;
			}
		}

		if(count<=5)
		{
			finalarray = new String[count][5];
			for(i=0;i<count;i++)
			{
				finalarray[k][0] = temparray[i][0];
				finalarray[k][1] = temparray[i][1];
				finalarray[k][2] = temparray[i][2];
				finalarray[k][3] = temparray[i][3];
				finalarray[k][4] = temparray[i][4];
				k++;
			}

		}
		else
		{
			int newcount = count-5;
			finalarray = new String[5][5];
			for(i=newcount;i<count;i++)
			{
				finalarray[k][0] = temparray[i][0];
				finalarray[k][1] = temparray[i][1];
				finalarray[k][2] = temparray[i][2];
				finalarray[k][3] = temparray[i][3];
				finalarray[k][4] = temparray[i][4];
				k++;
			}
		}
		return finalarray;
	}
}
