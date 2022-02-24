package parkingLotManagement;

import java.text.ParseException;

public interface Booking {
	String checkIn(ParkingLot p, String vnumber, String vtype, String entrytime, char lotname) throws ParseException;
	
	String checkOut(ParkingLot p, String vnumber, String exittime) throws ParseException;
	
	String findMyVehicle(ParkingLot p, String vnumber) throws ParseException;
}