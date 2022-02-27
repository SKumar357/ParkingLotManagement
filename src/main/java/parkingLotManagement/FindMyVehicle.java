package parkingLotManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Senthil kumar.V
 * @version 1.0
 * @since  2022-02-27
 */
public class FindMyVehicle extends HttpServlet{
	/**
	 *This service get the input parameters from the jsp and process the findMyVehicle methods and forwards the response to the jsp
	 */
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
				continue;
			}
			else if(arr[i][0].equals(vnumber))
			{
				count++;
			}
		}

		if(message.endsWith("in"))
		{
			httpServletRequest.setAttribute("arrmsg",count);
			httpServletRequest.setAttribute("parkingHistory",arr);
			httpServletRequest.setAttribute("message",message);
			httpServletRequest.setAttribute("message1","no");
			RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("findMyVehicle.jsp");
			dispatcher.forward(httpServletRequest,httpServletResponse);
		}
		else
		{
			httpServletRequest.setAttribute("arrmsg",count);
			httpServletRequest.setAttribute("parkingHistory",arr);
			httpServletRequest.setAttribute("message",message);
			httpServletRequest.setAttribute("message1","yes");
			RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("findMyVehicle.jsp");
			dispatcher.forward(httpServletRequest,httpServletResponse);
		}
	}
}