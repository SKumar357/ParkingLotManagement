package parkingLotManagement;

import java.io.IOException;
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
public class CheckIn extends HttpServlet{
	/**
	 *This service get the input parameters from the jsp and process the check-in methods and forwards the response to the jsp
	 */
	public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
		String message = "";
		String vnumber = httpServletRequest.getParameter("vnumber");
		String vtype = httpServletRequest.getParameter("vtype");
		String entrytime = httpServletRequest.getParameter("entrytime");
		char lotname = (httpServletRequest.getParameter("lotname").toUpperCase()).charAt(0);

		Booking b = new BookingImpl();
		ParkingLot p = new ParkingLotImpl();

		if((lotname>='A' && lotname<='E'))
		{
			try {
				message = b.checkIn(p,vnumber,vtype,entrytime,lotname);
				if(message.endsWith("full"))
				{
					httpServletRequest.setAttribute("message",message);
					httpServletRequest.setAttribute("message1","no");
					RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("checkIn.jsp");
					dispatcher.forward(httpServletRequest,httpServletResponse);
				}
				else
				{
					httpServletRequest.setAttribute("message",message);
					httpServletRequest.setAttribute("message1","yes");
					RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("checkIn.jsp");
					dispatcher.forward(httpServletRequest,httpServletResponse);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else
		{
			message = "Invalid Input";
			httpServletRequest.setAttribute("message",message);
			httpServletRequest.setAttribute("message1","no");
			RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("checkIn.jsp");
			dispatcher.forward(httpServletRequest,httpServletResponse);
		}



	}

}
