package parkingLotManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckOut extends HttpServlet{
	public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws UnsupportedEncodingException, IOException, ServletException {
		String vnumber = httpServletRequest.getParameter("vnumber");
		String exittime = httpServletRequest.getParameter("exittime");
		String message="";

		Booking b = new BookingImpl();
		ParkingLot p = new ParkingLotImpl();

		try {
			message = b.checkOut(p,vnumber,exittime);
			if(message.endsWith("in"))
			{
				httpServletRequest.setAttribute("message",message);
				httpServletRequest.setAttribute("message1","no");
				RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("checkOut.jsp");
				dispatcher.forward(httpServletRequest,httpServletResponse);
			}
			else
			{
				httpServletRequest.setAttribute("message",message);
				httpServletRequest.setAttribute("message1","yes");
				httpServletRequest.setAttribute("status", "*Vehicle checked out successfully");
				RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("checkOut.jsp");
				dispatcher.forward(httpServletRequest,httpServletResponse);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
