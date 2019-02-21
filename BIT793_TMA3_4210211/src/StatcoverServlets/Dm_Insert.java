package StatcoverServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import dataAccessor.MySQLAccessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dmCrud
 */
@WebServlet("/Dm_Insert")
public class Dm_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dm_Insert() {
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
		try {
		String Fname = request.getParameter("firstname");
		String Lname = request.getParameter("lastname");
		String afil = request.getParameter("affiliation");
		String email = request.getParameter("email");
		String contactNum = request.getParameter("contactNum");
		String pw = request.getParameter("password");

		// Connect to mysql and verify email and password
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			// loads driver
			Connection con = DriverManager.getConnection(MySQLAccessor.url, "root", MySQLAccessor.pw); // gets a new connection

			// SQL add dm
			String query = "INSERT INTO statcover_dm (dm_firstName, dm_lastName, dm_affiliation, dm_email, dm_contactNum, dm_password) values ('"+Fname+"','"+Lname+"','"+afil+"','"+email+"','"+contactNum+"','"+pw+"')";

			// create the statement
			PreparedStatement stmt = con.prepareStatement(query);

			// execute the statement
			stmt.executeUpdate();
			returnMessage = "The duty manager entry was inserted successfully. You can create another duty manager.";

		} catch (Exception e) {
			System.out.println(e);
			returnMessage = "An error occurred:" + e.getMessage();
		}
		
		request.setAttribute("message", returnMessage);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/dm_insert_form.jsp");
		dispatcher.forward(request, response);
		return;

	}
}
