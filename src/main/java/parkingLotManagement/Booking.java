package parkingLotManagement;

import java.text.ParseException;

/**
 * @author Senthil kumar.V
 * @version 1.0
 * @since  2022-02-27
 */
public interface Booking {
	/**
	 * This method is used to,
	 *  check the availability of the slots
	 *  if the customer entered slot is available and not full then the slot is alloted
	 *  else displays that the lot is full
	 *  
	 * @param p - object of the class ParkingLotImpl
	 * @param vnumber -  vehicle number of the vehicle
	 * @param vtype - type of the vehicle
	 * @param entrytime - entry time of the vehicle during check-in
	 * @param lotname - value of the parking lot (A,B,C,D,E)
	 * @return - the success message if the vehicle checked-in else displays parkinglot is full
	 * @throws ParseException
	 */
	String checkIn(ParkingLot p, String vnumber, String vtype, String entrytime, char lotname) throws ParseException;

	/**
	 * This method is used to,
	 *  check if the car is already in parked state
	 *  if the car is already parked then check-out the car along with displaying the amount to be padi
	 *  else displays that the vehicle is not checked-in
	 *  
	 * @param p - object of the class ParkingLotImpl
	 * @param vnumber -  vehicle number of the vehicle
	 * @param exittime - exit time of the vehicle during check-out
	 * @return - the success message if the vehicle checked-out else displays vehicle is not checked-in
	 * @throws ParseException
	 */
	String checkOut(ParkingLot p, String vnumber, String exittime) throws ParseException;

	/**
	 * This method is used to,
	 * check if the car is already in parked state
	 * if the car is already parked then displays the slot at which the vehicle is parked
	 * else displays that the vehicle is not checked-in
	 * it also displays the last five parking details of the vehicle
	 * 
	 * @param p - object of the class ParkingLotImpl
	 * @param vnumber -  vehicle number of the vehicle
	 * @return  - the success message if the vehicle checked-in else displays vehicle is not checked-in
	 * @throws ParseException
	 */
	String findMyVehicle(ParkingLot p, String vnumber) throws ParseException;
}