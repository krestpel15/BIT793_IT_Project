<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
	
<title>Acceptance Notifications</title>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
	
</head>
<body>
	<%@page import="java.sql.DriverManager"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.Statement"%>
	<%@page import="java.sql.Connection"%>
	<%@page import="dataAccessor.MySQLAccessor"%>
	<%@page import="utils.GlobalUtils"%> 

	<%
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = MySQLAccessor.url;
		String userName = "root";
		String password = MySQLAccessor.pw;
		Connection con;

		try {
			Class.forName(driverName).newInstance();
			con = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	%>
	<h2 align="center">
		<font><strong>Acceptance Notifications from STATCover</strong></font>
	</h2>
	
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/jsps/home.jsp">Home</a> | <a href="${pageContext.request.contextPath}/jsps/adminServices.jsp">Admin Services</a>
	<br>
	<br>
	
	<!--Show the message if any -->
	<label style="color: red">${message}</label>

	<!--Remove the message. We should do this otherwise the last message always appears on the page -->
	<%
		request.setAttribute("message", null);
	%>
	
	<form name="tables" method="post">
		<table class="table table-striped table-dark">
			<tr>

			</tr>
			<tr>
				<th><b>Acceptance Message Id</b></th>
				<th><b>Accepting Staff Id</b></th>
				<th><b>Acceptance Message</b></th>
				<th><b>Receiver Email</b></th>
				<th><b>Timestamp</b></th>
				<th><b>Request Id</b></th>
			</tr>

			<%
				try {
					con = DriverManager.getConnection(url, userName, password);
					Statement ps = con.createStatement();
					String query1 = "SELECT * FROM statcover_accept_notifications";

					ResultSet rs = ps.executeQuery(query1);
					while (rs.next()) {
			%>

			<tr>
				<td><%=rs.getInt("aMessage_id")%></td>
				<td><%=rs.getString("a_staff_id")%></td>
				<td><%=rs.getString("message")%></td>
				<td><%=rs.getString("receiver_email")%></td>
				<td><%=rs.getString("timestamp")%></td>
				<td><%=rs.getString("request_id")%></td>
			</tr>

			<%
				}

				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
			
		</table>
	</form>

</body>
</html>