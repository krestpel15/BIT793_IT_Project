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
 * Servlet implementation class Dm_Edit
 */
@WebServlet("/Dm_Edit")
public class Dm_Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dm_Edit() {
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
			String dmid = request.getParameter("dmid");
			int id = Integer.parseInt(dmid);
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

				// SQL update dm
				String query = "UPDATE statcover_dm SET dm_firstName='"+Fname+"', dm_lastName='"+Lname+"', dm_affiliation ='"+afil+"', dm_email='"+email+"', dm_contactNum ='"+contactNum+"',dm_password='"+pw+"' where dm_id='"+id+"'";

				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);
				returnMessage = "The duty manager entry was updated successfully. You can update another duty manager.";
				// execute the statement
				stmt.executeUpdate();

			} catch (Exception e) {
				System.out.println(e);
				returnMessage = "An error occurred:" + e.getMessage();
			}
			
			request.setAttribute("message", returnMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsps/tables_dm.jsp");
			dispatcher.forward(request, response);
			return;

	}

}
