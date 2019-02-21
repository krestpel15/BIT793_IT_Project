package StatcoverServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccessor.MySQLAccessor;

/**
 * Servlet implementation class Registration_Delete
 */
@WebServlet("/Registration_Delete")
public class Registration_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration_Delete() {
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
		try {
			String regid = request.getParameter("registration_id");
			int id = Integer.parseInt(regid);
			
			// Connect to mysql and verify email and password
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				// loads driver
				Connection con = DriverManager.getConnection(MySQLAccessor.url, "root", MySQLAccessor.pw); // gets a new connection

				// SQL delete staff
				String query = "DELETE from statcover_submitted_registration where registration_id='"+id+"'";

				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);

				// execute the statement
				stmt.executeUpdate();
				returnMessage = "The registration entry was rejected successfully. You can reject another registration.";
			
			} catch (Exception e) {
				System.out.println(e);
				returnMessage = "An error occurred:" + e.getMessage();
			}
			
			request.setAttribute("message", returnMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/tables_registrations.jsp");
			dispatcher.forward(request, response);
			return;
	}

}
