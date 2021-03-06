package StatcoverServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import dataAccessor.MySQLAccessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.GlobalUtils;

/**
 * Servlet implementation class Staff_Edit
 */
@WebServlet("/Staff_Edit")
public class Staff_Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Staff_Edit() {
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
			String staffid = request.getParameter("staffid");
			int id = Integer.parseInt(staffid);
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
				Connection con = DriverManager.getConnection(MySQLAccessor.url, "root", MySQLAccessor.pw); // gets a new connection

				// SQL update staff
				String query = "UPDATE statcover_staff SET staff_firstName='"+Fname+"', staff_lastName='"+Lname+"', staff_email='"+email+"', staff_contactNum='"+contactNum+"', staff_address='"+add+"', staff_specialty='"+specialty+"', staff_position='"+pos+"', staff_affiliation='"+affil+"', staff_licenseNum='"+licenseNum+"', staff_licenseExpiry=" + toDate + ", staff_password='"+pw+"' where staff_Id='"+id+"'";

				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);

				// execute the statement
				stmt.executeUpdate();
				returnMessage = "The staff entry was updated successfully. You can update another staff.";
			}
		} catch (Exception e) {
				System.out.println(e);
				returnMessage = "An error occurred:" + e.getMessage();
			}
			
			request.setAttribute("message", returnMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/tables_staff.jsp");
			dispatcher.forward(request, response);
			return;
	}

}
