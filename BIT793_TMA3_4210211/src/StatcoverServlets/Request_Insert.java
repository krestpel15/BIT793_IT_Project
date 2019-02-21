package StatcoverServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import dataAccessor.MySQLAccessor;
import utils.GlobalUtils;
import utils.JavaEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.statCover.availability;

/**
 * Servlet implementation class Request_Insert
 */
@WebServlet("/Request_Insert")
public class Request_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Request_Insert() {
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
			String pos = request.getParameter("position");
			String specialty = request.getParameter("specialty");

			String toDate = request.getParameter("shiftDate");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date utilDate = sdf.parse(toDate);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			String shift = request.getParameter("shift");
			String timeFro = request.getParameter("timefrom");
			String timeTo = request.getParameter("timeto");

			String quant = request.getParameter("qty").trim();
			int qty = Integer.parseInt(quant);

			String id = request.getParameter("dmid").trim();
			int dmid = Integer.parseInt(id);

			String email = request.getParameter("email");

			String today = GlobalUtils.quotedString(request.getParameter("reqdate"));

			UUID uuid = UUID.randomUUID();
			String guid = uuid.toString();

			// Connect to mysql and verify email and password

			Class.forName("com.mysql.cj.jdbc.Driver");
			// loads driver
			Connection con = DriverManager.getConnection(MySQLAccessor.url, "root", MySQLAccessor.pw); // gets a new connection
																								

			// SQL add request
			String query = "INSERT INTO statcover_requests (position, specialtyRequirement, shiftDate, shift, timeFrom, timeTo, quantity, dm_Id, dm_email, requestDate, guid) values ('"
					+ pos + "','" + specialty + "','" + sqlDate + "','" + shift + "','" + timeFro + "','" + timeTo
					+ "','" + qty + "','" + dmid + "','" + email + "'," + today + ",'" + guid + "')";

			// create the statement
			PreparedStatement stmt = con.prepareStatement(query);

			// execute the statement
			stmt.executeUpdate();
			
			returnMessage = "The shift request was inserted successfully. You can insert another request.";

			// Create a DB instance
			MySQLAccessor sa = new MySQLAccessor();
			
			// Get availability array
			ArrayList<availability> availList = sa.getAvailability(sqlDate, shift, pos, specialty);

			
			// for each availability record, send an e-mail
			ServletContext sc = getServletContext();
			String filePath = GlobalUtils.getFilePathInWebContentDirectory(sc,
					"/htmls/requestEmail.html");
			String emailHTML = "";

			JavaEmail emailObject = GlobalUtils.getEmailObject();
			if (emailObject == null) {
				returnMessage = "E-Mail object cannot be created!";
			} else {
				for (availability a : availList) // for every nurse, do these...
				{
					emailHTML = GlobalUtils.readFromFile(filePath);

					if (emailHTML.startsWith("Error")) {
						returnMessage = "The request was inserted but emails could not be sent! The Error message is: "
								+ emailHTML;
					} else {

						// Replace the parameters in emailHTML
						emailHTML = emailHTML.replace("[Name]", a.getStaffFname());
						emailHTML = emailHTML.replace("[Surname]", a.getStaffLname());
						emailHTML = emailHTML.replace("[Day]", toDate);
						emailHTML = emailHTML.replace("[Shift]", shift);
						emailHTML = emailHTML.replace("[From]", timeFro);
						emailHTML = emailHTML.replace("[To]", timeTo);
						emailHTML = emailHTML.replace("[DomainAndPort]", GlobalUtils.getBaseUrl(request));
						emailHTML = emailHTML.replace("[Guid]", guid);
						emailHTML = emailHTML.replace("[Staffid]", String.valueOf(a.getStaffId()));
						emailObject.sendEmail(a.getEmail(), "Request", emailHTML);

						// Add some code to insert a record for request_notification table
						sa.addRequestNotification(Integer.valueOf(id), a.getEmail(), emailHTML);
					} // end of else
				} // end of for

				request.setAttribute("dmid", id);
				request.setAttribute("email", email);

			}
		} catch (Exception e) {
			System.out.println(e);
			returnMessage = "An error occurred:" + e.getMessage();
		}
		
		request.setAttribute("message", returnMessage);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/request_form.jsp");
		dispatcher.forward(request, response);
		return;
	}
}
