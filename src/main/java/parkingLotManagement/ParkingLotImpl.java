package parkingLotManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

public class ParkingLotImpl implements ParkingLot{
	static int carparkingrate =50; // used to store parking rate per hour for car
	static int bikeparkingrate =10; // used to store parking rate per hour for bike
	static String[][] parkinglots = new String[5][10]; // used to store total parking lots
	static int slots=5;// number of space available per each slots
	static HashMap<String, Date> price = new HashMap<String, Date>(); // used to find price while check-out
	static HashMap<String, Character> freearray = new HashMap<String, Character>(); // used to find and freeup space from lots once check-out happens
	static HashMap<String, String> vehicletype = new HashMap<String, String>(); // used to ge the vehicle type
	static SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T'hh:mm"); // Used to format date
	static int parkingcount=0;
	static String[][] parkingHistory = new String[50][5];
	static String[][] temparray = new String[20][5];
	
	@Override
	public String[][] getParkingHistory(String vnumber) {
		/*for(int i=0;i<50;i++)
		{
			//System.out.println("INSIDE COMPARISON");
			//System.out.println(parkingHistory[i][0]);
			if(parkingHistory[i][0]==null)
			{
				//System.out.println("************************CONTINUED inside METHOD**********************");
				continue;
			}
			if(((parkingHistory[i][0]).equals(vnumber)))
			{
				temparray[i][0] = parkingHistory[i][0];
				temparray[i][1] = parkingHistory[i][1];
				temparray[i][2] = parkingHistory[i][2];
				temparray[i][3] = parkingHistory[i][3];
				temparray[i][4] = parkingHistory[i][4];
				System.out.println("i value : "+i);
				System.out.println(temparray[i][0]);
						System.out.println(temparray[i][1]);
								System.out.println(temparray[i][2]);
										System.out.println(temparray[i][3]);
												System.out.println(temparray[i][4]);
												System.out.println("*****************************************");
			}
		}*/
		String[][] temparray = new String[10][5];
		String[][] finalarray;
		int i=0,j=0,k=0;
		int count = 0;
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
				//System.out.println("Inside count method");
				System.out.print(count);
			}
		}
		System.out.println("COUNT : "+count);
		
		if(count<=5)
		{
			//System.out.println("Inside IF count<2");
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
			//System.out.println("Inside IF count>2");
			int newcount = count-5; //1,3
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

	@Override
	public String allocate(int lotvalue, String vnumber, String vtype, String entrytime, char lotname) throws ParseException
	{
		Date date = formatter.parse(entrytime);
		price.put(vnumber, date);
		freearray.put(vnumber, lotname);
		vehicletype.put(vnumber, vtype);
		for(int i=0;i<slots;i++)
		{
			if(parkinglots[lotvalue][i]==null)
			{
				parkinglots[lotvalue][i] = vnumber;
				return("*The Vehicle is parked at "+lotname+(i+1));
			}
		}
		return null;
	}

	@Override
	public String getslot(String vnum)
	{
		Character lotname = freearray.get(vnum);
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

	@Override
	public Date getentrytime(String vnumber) {
		Date time = (price.get(vnumber));
		return time;
	}

	@Override
	public String freespace(String vnumber) {
		price.remove(vnumber);
		String lotname = null;
		int lot = (freearray.get(vnumber)-65);
		for(int i=0;i<slots;i++)
		{
			if(((parkinglots[lot][i])!=null)&&((parkinglots[lot][i]).equals(vnumber)))
			{
				parkinglots[lot][i] = null;
				lotname = String.valueOf(lot)+String.valueOf(i);
				freearray.remove(vnumber);
			}
		}
		return lotname;

	}

	@Override
	public boolean coCheck(String vnum)
	{
		return price.containsKey(vnum);
	}

	@Override
	public String calculatePrice(String vnum, String exittime) throws ParseException {
		Date entrytime= price.get(vnum);
		Date exittime1 = formatter.parse(exittime);
		long hours =((exittime1.getTime()-entrytime.getTime())/(3600000));
		float mins =((exittime1.getTime()-entrytime.getTime())/(60000))-(hours*60);
		String vtype = vehicletype.get(vnum);
		String lot = getslot(vnum);

		parkingHistory[parkingcount][0] =vnum;
		parkingHistory[parkingcount][1] =lot;
		parkingHistory[parkingcount][2] =new SimpleDateFormat("dd/MM/yyy").format(entrytime);
		parkingHistory[parkingcount][3] =new SimpleDateFormat("hh:mm a").format(entrytime);
		parkingHistory[parkingcount][4] =new SimpleDateFormat("hh:mm a").format(exittime1);
		parkingcount++;

		//Arrays.stream(parkingHistory).forEach((x)-> System.out.println(x);)

		if(vtype.equals("CAR"))
		{
			int pricetobepaid = (int) ((hours)*(carparkingrate) + ((mins/60) * (carparkingrate)));
			return ("Vehicle ("+vnum+") is available in "+lot+" \nThe parking duration of :"+hours+" hours:"+(int)mins+" mins costs Rs.:"+pricetobepaid);
		}
		else
		{
			int pricetobepaid = (int) ((hours)*(bikeparkingrate) + ((mins/60) * (bikeparkingrate)));
			return ("Vehicle ("+vnum+") is available in "+lot+" \nThe parking duration of :"+hours+" hours:"+(int)mins+" mins costs Rs.:"+pricetobepaid);
		}
	}

	@Override
	public boolean fmvcheck(String vnum)
	{
		return price.containsKey(vnum);
	}

	@Override
	public boolean ciCheck(int lotvalue) {
		for(int i=0;i<slots;i++)
		{
			if(parkinglots[lotvalue][i]==null)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String[][] showAvailableSlots() {
		int acount=0,bcount=0,ccount=0,dcount=0,ecount=0;
		Collection<Character> value = freearray.values();
		String[][] availableSlots = new String[5][4];
		for(Character x:value)
		{
			if(x.equals('A'))
			{
				acount++;
			}
			if(x.equals('B'))
			{
				bcount++;
			}
			if(x.equals('C'))
			{
				ccount++;
			}
			if(x.equals('D'))
			{
				dcount++;
			}
			if(x.equals('E'))
			{
				ecount++;
			}
		}
		availableSlots[0][0]="A";
		availableSlots[1][0]="B";
		availableSlots[2][0]="C";
		availableSlots[3][0]="D";
		availableSlots[4][0]="E";

		availableSlots[0][1]="10";
		availableSlots[1][1]="10";
		availableSlots[2][1]="10";
		availableSlots[3][1]="10";
		availableSlots[4][1]="10";

		availableSlots[0][2]=Integer.toString(acount);
		availableSlots[1][2]=Integer.toString(bcount);
		availableSlots[2][2]=Integer.toString(ccount);
		availableSlots[3][2]=Integer.toString(dcount);
		availableSlots[4][2]=Integer.toString(ecount);

		availableSlots[0][3]=Integer.toString(10-acount);
		availableSlots[1][3]=Integer.toString(10-bcount);
		availableSlots[2][3]=Integer.toString(10-ccount);
		availableSlots[3][3]=Integer.toString(10-dcount);
		availableSlots[4][3]=Integer.toString(10-ecount);


		return availableSlots;
	}

}
