<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/select.css"
	type="text/css">
<meta charset="ISO-8859-1">
<title>Duty Manager Form</title>

</head>

<body>
	<%@page import="java.sql.DriverManager"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.Statement"%>
	<%@page import="java.sql.Connection"%>
	<%@page import="dataAccessor.MySQLAccessor"%>
	<%@page import="utils.GlobalUtils"%> 

	<%
 		String id = request.getParameter("dm_id");
 		if (id==null || id.isEmpty()){
 		out.println("id is missing");
 		return;
 			}
 		
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
	
		<%
				try {
					con = DriverManager.getConnection(url, userName, password);
					Statement ps = con.createStatement();
					String query1 = "SELECT * FROM statcover_dm where dm_id=" + id;

					ResultSet rs = ps.executeQuery(query1);
					while (rs.next()) {
						
						String affil=rs.getString("dm_affiliation");
			%>
	
	<img src="${pageContext.request.contextPath}/images/statCover.png"
		alt="STATCover Logo"
		style="float: center; width: 600px; height: 200px;">
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/jsps/home.jsp">Home</a> | <a href="${pageContext.request.contextPath}/jsps/adminServices.jsp">Admin Services</a>
	<br>
	<br>

	<h3>Please complete your details</h3>

	<form id="dmEdit" method="post" action="${pageContext.request.contextPath}/StatcoverServlets.Dm_Edit">

		<fieldset class="field-set">
			<legend>Duty Manager Details</legend>

			<div class="field-group">
				<!-- Duty Manager ID should not be empty and should be numeric only -->
				<label for="dmid">Duty Manager ID:</label> <input type="text"
					name="dmid" id="dmid" size="5" maxlength="12"
					value ="<%=rs.getString("dm_id")%>" readonly/>
			</div>

			<div class="field-group">
				<!-- First name should not be empty and should be a string not a number -->
				<label for="firstname">First name:</label> <input type="text"
					name="firstname" id="firstname" size="10" maxlength="40"
					required="required" pattern="[A-Za-z]{2,}"
					title="two or more alphabets only"
					value ="<%=rs.getString("dm_firstname")%>"/>
			</div>

			<div class="field-group">
				<!-- Last name should not be empty and should be a string not a number -->
				<label for="lastname">Last Name:</label> <input type="text"
					name="lastname" id="lastname" size="10" maxlength="40"
					required="required" pattern="[A-Za-z]{2,}"
					title="two or more alphabets only"
					value ="<%=rs.getString("dm_lastname")%>"/>
			</div>

			<div class="field-group">
				<!-- Affiliation should not be empty and should be a string not a number  -->
				<label for="affiliation">Affiliation:</label> 
				<select required id="affiliation" name="affiliation">
					<option value="">Select One</option>
					<option <%=GlobalUtils.isSelected("MMH", affil)%> value="MMH">MMH</option>
				</select>
			</div>

			<div class="field-group">
				<!-- Email should not be empty and should contain at least one @ -->
				<label for="email">Email:</label> <input type="text" name="email"
					id="email" size="10" maxlength="40" required="required"
					pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
					title="Valid email address only, must contain at least one @ and .com"
					value ="<%=rs.getString("dm_email") %>"/>
			</div>

			<div class="field-group">
				<!-- Contact number should not be empty and contain numeric only -->
				<label for="contactNum">Contact Number:</label> <input type="text"
					name="contactNum" id="contactNum" size="7" maxlength="12"
					required="required" pattern="(^[0][2][12579]{1})(\d{6,7}$)"
					title="valid contact numbers only"
					value ="<%=rs.getString("dm_contactNum") %>"/>
			</div>

			<div class="field-group">
				<!-- Password should not be empty and should be Alphanumeric -->
				<label for="password">Password:</label> <input type="password"
					name="password" id="password" size="20" maxlength="50"
					required="required" pattern=".{6,}" title="Six or more characters"
					value ="<%=rs.getString("dm_password") %>"/>
			</div>

		</fieldset>

		<input type="submit" value="Update" />
	</form>
	
	<%
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
</body>
</html>