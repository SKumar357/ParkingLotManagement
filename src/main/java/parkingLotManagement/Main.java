package parkingLotManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

//main method
public class Main {
	public static void main(String[] args) throws ParseException, IOException {
		Booking b = new BookingImpl();
		ParkingLot p = new ParkingLotImpl();
		System.out.println("Welcome to parking lot :");
		Scanner sc =new Scanner(System.in);
		String vnumber, vtype, entrytime, exittime = null;
		char lotname;
		boolean loop =true;
		while(loop)
		{
			System.out.println("\n********************");
			System.out.println("1.Check-in\n2.Check-out\n3.Find-my-vehicle\n4.Exit");
			System.out.println("********************");
			int choice =sc.nextInt();
			switch(choice)
			{
			case 1:
			{
				System.out.println("Enter vehicle number");
				vnumber = sc.next();
				System.out.println("Enter vehicle type(car/bike)");
				vtype = sc.next();
				System.out.println("Enter Entry time (Sample : 31.12.1998-1:00-PM)");
				entrytime = sc.next();
				System.out.println("Enter lot name(A-Y)");
				lotname = (sc.next().toUpperCase()).charAt(0);
				if((vtype.equals("car")||vtype.equals("bike")) && (lotname>='A' && lotname<='Y'))
				{
					b.checkIn(p,vnumber,vtype,entrytime,lotname);
				}
				else
				{
					System.out.println("provide valid input");
					break;
				}

				break;
			}
			case 2:
			{
				System.out.println("Enter vehicle number");
				vnumber = sc.next();
				System.out.println("Enter Exit time (Sample : 31.12.1998-3:20-PM)");
				exittime = sc.next();
				b.checkOut(p,vnumber,exittime);
				break;
			}
			case 3:
			{
				System.out.println("Enter vehicle number");
				vnumber = sc.next();
				b.findMyVehicle(p,vnumber);
				break;
			}
			case 4:
			{
				loop=false;
				//p.printAvailableDetails();
				System.out.println("Exiting...");
				break;
			}
			}
		}
	}
}
