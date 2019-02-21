package StatcoverServlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.statCover.admin;

/**
 * Servlet implementation class LoginCheck_admin
 */
@WebServlet("/LoginCheck_admin")
public class LoginCheck_admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	dataAccessor.MySQLAccessor accessor;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck_admin() {
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

		ArrayList<admin> admins = accessor.getAdmin();
		for (admin admin : admins) {

			if (admin.getAdminEmail().matches(email) && admin.getAdminPassword().matches(pw)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("admin", admin.getAdminFname() + " " + admin.getAdminLname());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/adminServices.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		returnMessage = "Invalid email and/or password. Please try again.";
		request.setAttribute("message", returnMessage);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/loginAdmin.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
