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
 * Servlet implementation class Staff_Delete
 */
@WebServlet("/Staff_Delete")
public class Staff_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Staff_Delete() {
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
			String staffid = request.getParameter("staff_Id");
			int id = Integer.parseInt(staffid);
			
			// Connect to mysql and verify email and password
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				// loads driver
				Connection con = DriverManager.getConnection(MySQLAccessor.url, "root", MySQLAccessor.pw); // gets a new connection

				// SQL delete staff
				String query = "DELETE from statcover_staff where staff_Id='"+id+"'";

				// create the statement
				PreparedStatement stmt = con.prepareStatement(query);

				// execute the statement
				stmt.executeUpdate();
				returnMessage = "The staff entry was deleted successfully. You can delete another staff.";
			
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
