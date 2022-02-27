package parkingLotManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Senthil kumar.V
 * @version 1.0
 * @since  2022-02-27
 */
public class ShowSlots extends HttpServlet{
	/**
	 *This service get the input parameters from the jsp and process the showSLots methods and forwards the response to the jsp
	 */
	public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws UnsupportedEncodingException, IOException, ServletException {

		ParkingLot p = new ParkingLotImpl();
		String arr[][] = p.showAvailableSlots();
		httpServletRequest.setAttribute("arr",arr);
		RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("showSlots.jsp");
		dispatcher.forward(httpServletRequest,httpServletResponse);
	}
}
