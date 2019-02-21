package StatcoverServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.statCover.*;

/**
 * Servlet implementation class loginCheck
 */
@WebServlet("/LoginCheck_dm")
public class LoginCheck_dm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	dataAccessor.MySQLAccessor accessor;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck_dm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String returnMessage = "";
		String email = request.getParameter("email");
		String pw = request.getParameter("password");

		// Connect to mysql and verify email and password

		accessor = new dataAccessor.MySQLAccessor();

		ArrayList<dutyManager> dms = accessor.getDutyManagers();
		for (dutyManager dm : dms) {

			if (dm.getDmEmail().matches(email) && dm.getDmPassword().matches(pw)) {
				request.setAttribute("email", email);
				request.setAttribute("dmid", dm.getDmId());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/request_form.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		
		returnMessage = "Invalid email and/or password. Please try again.";
		request.setAttribute("message", returnMessage);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/loginDm.jsp");
		dispatcher.forward(request, response);
		return;
	}
}
