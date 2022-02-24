package parkingLotManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSlots extends HttpServlet{
	public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws UnsupportedEncodingException, IOException, ServletException {

		Booking b = new BookingImpl();
		ParkingLot p = new ParkingLotImpl();
		String arr[][] = p.showAvailableSlots();
		httpServletRequest.setAttribute("arr",arr);
		RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("showSlots.jsp");
		dispatcher.forward(httpServletRequest,httpServletResponse);
	}
}
