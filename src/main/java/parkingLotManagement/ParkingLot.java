package parkingLotManagement;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Senthil kumar.V
 * @version 1.0
 * @since  2022-02-27
 */
public interface ParkingLot {

	/**
	 * This function is used to check in the parkinglots array for the
	 * particular slot(A or B or C or D or E) that user have asked for,
	 * if there is space for the vehicle to get parked
	 * 
	 * @param lotvalue
	 * @return - boolean true if the space is available in parking lot, boolean false if there is no space available in parking lot
	 */
	boolean ciCheck(int lotvalue);
	
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
	String allocate(int lotvalue, String vnumber, String vtype, String entrytime, char lotname) throws ParseException;
	
	/**
	 * This function is used to check in the pricecache array if the vehicle is already checked-in or not
	 * 
	 * @param vnum - vehicle number of the vehicle
	 * @return boolean true if the vehicle is available in pricecache, boolean false if the vehicle is not available in pricecache
	 */
	boolean coCheck(String vnum);
	
	/**
	 * This function is used to calculate the price for the total duration vehicle has been parked in the parking
	 * it then populates the parkinghistorycache with the checked-out car details
	 * 
	 * @param vnum - vehicle number of the vehicle
	 * @param exittime - exit time of the vehicle during check-out
	 * @return - success messsge along with vehcile number, lotname, hours, amount to be paid
	 * @throws ParseException
	 */
	String calculatePrice(String vnum, String exittime) throws ParseException;
	
	/**
	 * This function is used to remove the slot for the vehicle after the Check-out is complete
	 * removes the value of the vehicle number and entry time to the pricecache
	 * removes the value of the vehicle number and lotname to the freearraypricecache
	 * removes the value of the vehicle type and entry time to the vehicletypecache
	 * 
	 * @param vnumber - vehicle number of the vehicle
	 */
	void freeSpace(String vnumber);
	
	/**
	 * This function is used to check in the pricecache array if the vehicle is already checked-in or not
	 * 
	 * @param vnumber - vehicle number of the vehicle
	 * @return boolean true if the vehicle is available in pricecache, boolean false if the vehicle is not available in pricecache
	 */
	boolean fmvCheck(String vnumber);
	
	/**
	 * This method is used to find the slotname and number at which the vehicle is parked
	 * 
	 * @param vnum - vehicle number of the vehicle
	 * @return the slotname along with the slotnumber
	 */
	String getslot(String vnum);
	
	/**
	 * This method is used to get the check-in time of the vehicle from the pricecache
	 * 
	 * @param vnumber - vehicle number of the vehicle
	 * @return time - check-in time
	 * @throws ParseException
	 */
	Date getEntryTime(String vnumber) throws ParseException;
	
	/**
	 * This method is used to check in the freeararycache and populate the remaining available slot details in all the slots
	 * 
	 * @return availableSlots - it contains the total available slots details in all the slots
	 */
	String[][] showAvailableSlots();

	/**
	 * This method is used to get the lot value from the stored redis cache (parkinglotscache)
	 */
	void getParkingLotCache();
	
	/**
	 *  This method is used to get the parkinghistory the stored redis cache (parkinghistorycache)
	 */
	void getParkingHistoryCache();
	
	/**
	 * This method is used to iterate through the parkignhistory cache and find the last five parking history of the vehicle
	 * 
	 * @param vnumber - vehicle number of the vehicle
	 * @return - finalarray with the last five parking history of the vehicle
	 */
	String[][] getParkingHistory(String vnumber);

}