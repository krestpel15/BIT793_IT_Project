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
 * Servlet implementation class Registration_Insert
 */
@WebServlet("/Registration_Insert")
public class Registration_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration_Insert() {
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
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String gen = request.getParameter("gender");
			String email = request.getParameter("email");
			String contactNum =request.getParameter("contactNum");
			String add = request.getParameter("address");
			String specialty = request.getParameter("specialty");
			String pos = request.getParameter("position");
			String yrs = request.getParameter("yearsofex").trim();
			int years = Integer.parseInt(yrs);
			
			// Connect to mysql and verify email and password
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			// loads driver
			Connection con = DriverManager.getConnection(MySQLAccessor.url, "root", MySQLAccessor.pw); // gets a new connection

			// SQL add registration
			String query = "INSERT INTO statcover_submitted_registration (firstName, lastName, gender, email, contactNum, address, specialty, position, yearsOfExperience) values ('"+fname+"', '"+lname+"', '"+gen+"','"+email+"', '"+contactNum+"', '"+add+"', '"+specialty+"', '"+pos+"', '"+years+"')";

			// create the statement
			PreparedStatement stmt = con.prepareStatement(query);

			// execute the statement
			stmt.executeUpdate();
			returnMessage = "Your registration entry was inserted successfully. Thank you for registering with STATCover.";
			
		} catch (Exception e) {
			System.out.println(e);
			returnMessage = "An error occurred:" + e.getMessage();
		}
			
			request.setAttribute("message", returnMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/registration_form.jsp");
			dispatcher.forward(request, response);
			return;
			
	}
}
