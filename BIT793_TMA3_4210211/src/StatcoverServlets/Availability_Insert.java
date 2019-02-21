package StatcoverServlets;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class Availability_Insert
 */
@WebServlet("/Availability_Insert")
public class Availability_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Availability_Insert() {
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
			
			String toDate = request.getParameter("availDate");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date utilDate= sdf.parse(toDate);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			String shift = request.getParameter("shift");

			// Connect to mysql and verify email and password
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				// loads driver
				Connection con = DriverManager.getConnection(MySQLAccessor.url, "root", MySQLAccessor.pw); // gets a new connection

				// SQL add availability
				String query = "INSERT INTO statcover_staff_availability (staff_id, availability_date, preferred_shift) values ('"+id+"', '"+sqlDate+"', '"+shift+"')";

				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);

				// execute the statement
				stmt.executeUpdate();
				
				returnMessage = "The staff availability entry was inserted successfully. You can insert another availability.";
				
				request.setAttribute("staffid", staffid);
				request.setAttribute("staffName", request.getParameter("firstname"));
				request.setAttribute("staffLname", request.getParameter("lastname"));
				request.setAttribute("staffLicense", request.getParameter("licenseNum"));
				
	///////////////THE FOLLOWING CODE BLOCK IS FOR REQUEST NOTIFICATIONS//////////
					//Create a DB instance
					MySQLAccessor sa=new MySQLAccessor();
					
					//Get availability array
					availability avail=sa.getAvailability(id, sqlDate, shift);
					
					if (avail==null)
					{
						returnMessage+="However, there is no available requests at the moment.";
						return;
					}

					ServletContext sc=getServletContext();
					String filePath=GlobalUtils.getFilePathInWebContentDirectory(getServletContext(),"/htmls/requestEmail.html");
					String emailHTML="";


					JavaEmail emailObject=GlobalUtils.getEmailObject();
					if (emailObject==null)
					{
						returnMessage+=" However, notification e-mails could not be sent!";	
						return;
					}
					
									

					emailHTML=GlobalUtils.readFromFile(filePath);
					
					if (emailHTML.startsWith("Error"))
					{
						returnMessage+="However, available request could not be checked! The Error message is: "+emailHTML;
						return;
					}
				
					//Replace the parameters in emailHTML
					emailHTML=emailHTML.replace("[Name]", avail.getStaffFname());
					emailHTML=emailHTML.replace("[Surname]", avail.getStaffLname());
					emailHTML=emailHTML.replace("[Day]",toDate);
					emailHTML=emailHTML.replace("[Shift]",shift);
					emailHTML=emailHTML.replace("[From]",avail.getTimeFrom());
					emailHTML=emailHTML.replace("[To]",avail.getTimeTo());
					emailHTML=emailHTML.replace("[DomainAndPort]", GlobalUtils.getBaseUrl(request));
					emailHTML=emailHTML.replace("[Guid]",avail.getRequestGUID());
					emailHTML=emailHTML.replace("[Staffid]",String.valueOf(avail.getStaffId()));
					emailObject.sendEmail(avail.getEmail(),"Request",emailHTML);
						
					//Add some code to insert a record for request_notification table
					sa.addRequestNotification(avail.getdmid(), avail.getEmail(),emailHTML);
				
					
			} catch (Exception e) {
				System.out.println(e);
				returnMessage = "An error occurred:" + e.getMessage();				
			} 
		
			finally 
			{
				request.setAttribute("message", returnMessage);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/availability_form.jsp");
				dispatcher.forward(request, response);
				return;
			}
	}
}
