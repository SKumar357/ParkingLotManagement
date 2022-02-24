package parkingLotManagement;

import java.text.ParseException;
import java.util.Date;

public interface ParkingLot {

	String allocate(int lotvalue, String vnumber, String vtype, String entrytime, char lotname) throws ParseException;

	String getslot(String vnum);

	String freespace(String vnumber);

	boolean coCheck(String vnum);

	String calculatePrice(String vnum, String exittime) throws ParseException;

	boolean fmvcheck(String vnumber);

	boolean ciCheck(int lotvalue);

	String[][] showAvailableSlots();

	Date getentrytime(String vnumber);

	String[][] getParkingHistory(String vnumber);

}