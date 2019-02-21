package StatcoverServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.statCover.staff;

/**
 * Servlet implementation class LoginCheck_staff
 */
@WebServlet("/LoginCheck_staff")
public class LoginCheck_staff extends HttpServlet {
	private static final long serialVersionUID = 1L;
	dataAccessor.MySQLAccessor accessor;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck_staff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String returnMessage = "";
		String email = request.getParameter("email");
		String pw = request.getParameter("password");

		// Connect to mysql and verify email and password

		accessor = new dataAccessor.MySQLAccessor();

		ArrayList<staff> staff = accessor.getStaff();
		for (staff s : staff) {

			if (s.getStaffEmail().matches(email) && s.getStaffPassword().matches(pw)) {
				request.setAttribute("staffid", s.getStaffId());
				request.setAttribute("staffName", s.getStaffFname());
				request.setAttribute("staffLname", s.getStaffLname());
				request.setAttribute("staffLicense", s.getStaffLicenseNum());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/availability_form.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		returnMessage = "Invalid email and/or password. Please try again.";
		request.setAttribute("message", returnMessage);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/loginStaff.jsp");
		dispatcher.forward(request, response);
		return;

	}

}
