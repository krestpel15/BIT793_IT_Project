package StatcoverServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccessor.MySQLAccessor;
import utils.GlobalUtils;

/**
 * Servlet implementation class Staff_Insert
 */
@WebServlet("/Staff_Insert")
public class Staff_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Staff_Insert() {
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
			String email = request.getParameter("email");
			String contactNum = request.getParameter("contactNum");
			String add = request.getParameter("address");
			String specialty = request.getParameter("specialty");
			String pos = request.getParameter("position");
			String affil = request.getParameter("affiliation");
			String licenseNum = request.getParameter("licenseNum");

			java.util.Date utilDate = null;
			String toDate = request.getParameter("licenseExpiry");

			if (toDate != null && !toDate.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				utilDate = sdf.parse(toDate);
			}

			// date control if position is "RN"
			if (utilDate == null && pos.equals("RN")) {
				//do nothing
			} else {

				toDate = GlobalUtils.dateToStringQuoted(utilDate);

				String pw = request.getParameter("password");

				// Connect to mysql and verify email and password

				Class.forName("com.mysql.cj.jdbc.Driver");
				// loads driver

				Connection con = DriverManager.getConnection(MySQLAccessor.url, "root", MySQLAccessor.pw); // gets a new
																											// connection

				// SQL add staff
				String query = "INSERT INTO statcover_staff (staff_firstName, staff_lastName, staff_email, staff_contactNum, staff_address, staff_specialty, staff_position, staff_affiliation, staff_licenseNum, staff_licenseExpiry, staff_password) values ('"
						+ Fname + "','" + Lname + "','" + email + "','" + contactNum + "','" + add + "','" + specialty
						+ "','" + pos + "','" + affil + "','" + licenseNum + "'," + toDate + ",'" + pw + "')";

				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);

				// execute the statement
				stmt.executeUpdate();
				returnMessage = "The staff entry was inserted successfully. You can create another staff.";
			}
		} catch (Exception e) {
			System.out.println(e);
			returnMessage = "An error occurred:" + e.getMessage();
		}

		request.setAttribute("message", returnMessage);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/staff_insert_form.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
