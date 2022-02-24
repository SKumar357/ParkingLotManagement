package parkingLotManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FindMyVehicle extends HttpServlet{
	public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws UnsupportedEncodingException, IOException, ServletException {
		String vnumber = httpServletRequest.getParameter("vnumber");
		String message="";

		Booking b = new BookingImpl();
		ParkingLot p = new ParkingLotImpl();
		try {
			message = b.findMyVehicle(p,vnumber);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String arr[][] = p.getParkingHistory(vnumber);

		int count=0;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i][0]==null)
			{
				//System.out.println("************************CONTINUED inside jsp**********************");
				continue;
			}
			else if(arr[i][0].equals(vnumber))
			{
				count++;
				System.out.println("Inside count method");
				System.out.print(count);
			}
		}

		if(count==0)
		{

		}
		/*String[][] temparray = new String[10][5];
		String[][] finalarray;
		//System.out.println("ENTERED");
		int i=0,j=0,k=0;
		int count = 0;
		for(i=0;i<arr.length;i++)
		{
			if(arr[i][0]==null)
			{
				//System.out.println("************************CONTINUED inside jsp**********************");
				continue;
			}
			else if(arr[i][0].equals(vnumber))
			{
				temparray[j][0] = arr[i][0];
				temparray[j][1] = arr[i][1];
				temparray[j][2] = arr[i][2];
				temparray[j][3] = arr[i][3];
				temparray[j][4] = arr[i][4];
				count++;
				j++;
				System.out.println("Inside count method");
				System.out.print(count);
			}
		}
		System.out.println("COUNT : "+count);

		if(count<=2)
		{
			System.out.println("Inside IF count<2");
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
			System.out.println("Inside IF count>2");
			int newcount = count-2; //1,3
			finalarray = new String[newcount+1][5];
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
		System.out.println("INCOMING");
		System.out.println("INCOMING arr lenght : "+arr.length);
		Stream.of(arr)
		.flatMap(Stream::of)
		.forEach(System.out::println);

		System.out.println("TEMP ARRAY");
		System.out.println("TEMP ARRAY lenght : "+temparray.length);
		Stream.of(temparray)
		.flatMap(Stream::of)
		.forEach(System.out::println);

		System.out.println("FINAL ARRAY");
		System.out.println("FINAL ARRAY lenght : "+finalarray.length);
		Stream.of(finalarray)
		.flatMap(Stream::of)
		.forEach(System.out::println);
		 */
		if(message.endsWith("in"))
		{
				httpServletRequest.setAttribute("arrmsg",count);
				System.out.println("inside ends with count :"+count);
				httpServletRequest.setAttribute("parkingHistory",arr);
				httpServletRequest.setAttribute("message",message);
				httpServletRequest.setAttribute("message1","no");
				RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("findMyVehicle.jsp");
				dispatcher.forward(httpServletRequest,httpServletResponse);
		}
		else
		{
			System.out.println("inside else count :"+count);
			httpServletRequest.setAttribute("arrmsg",count);
			httpServletRequest.setAttribute("parkingHistory",arr);
			httpServletRequest.setAttribute("message",message);
			httpServletRequest.setAttribute("message1","yes");
			RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("findMyVehicle.jsp");
			dispatcher.forward(httpServletRequest,httpServletResponse);
		}
	}
}