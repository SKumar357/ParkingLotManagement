package parkingLotManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingImpl implements Booking{

	//check-in methods
	@Override
	public String checkIn(ParkingLot p,String vnumber, String vtype, String entrytime, char lotname) throws ParseException
	{
		int lotvalue = (int)(lotname)-65;
		if(p.ciCheck(lotvalue))
		{
			return p.allocate(lotvalue,vnumber,vtype,entrytime,lotname);
			//p.printDetails();
		}
		else
		{
			return("*Lot is full");
		}
	}

	//check-out methods
	@Override
	public String checkOut(ParkingLot p, String vnumber, String exittime) throws ParseException
	{
		String message="";
		if(p.coCheck(vnumber))
		{
			message = p.calculatePrice(vnumber,exittime);
			p.freespace(vnumber);
		}
		else
		{
			return("Currently, Vehicle("+vnumber+") is not checked in");
			//p.printDetails();
		}
		return message;
	}

	//findvehicle methods
	@Override
	public String findMyVehicle(ParkingLot p, String vnumber) throws ParseException
	{
		if(p.fmvcheck(vnumber))
		{
			String lot = p.getslot(vnumber);
			Date time =(p.getentrytime(vnumber));
			String altered = new SimpleDateFormat("hh:mm a").format(time);
			return("Vehicle "+vnumber+" is parked at the "+lot+" lot at "+altered);
		}
		else
		{
			return("Currently Vehicle "+vnumber+" is not checked in");
		}

	}
}