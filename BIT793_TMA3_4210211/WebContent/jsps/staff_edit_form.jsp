<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-GB">
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/select.css"
	type="text/css">
<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$('#datepicker').datepicker();
	});
</script>

<script>
function validate() {
	var selectBox = document.getElementById('position');
	var userInput = selectBox.options[selectBox.selectedIndex].value;
	if (userInput == 'RN') {
		if (document.getElementById('specialty').value == "") {
			alert('All fields are require.');
			return false;
		}
		if (document.getElementById('licenseNum').value == "") {
			alert('All fields are require.');
			return false;
		}
		if (document.getElementById('datepicker').value == "") {
			alert('All fields are require.');
			return false;
		} else {
			return true;
		}
	}
}
</script>

<title>Staff Edit Form</title>
</head>
<body>
	<%@page import="java.sql.DriverManager"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.Statement"%>
	<%@page import="java.sql.Connection"%>
	<%@page import="dataAccessor.MySQLAccessor"%>
	<%@page import="utils.GlobalUtils"%> 
	
	
	<%
 			String id = request.getParameter("staff_Id");
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
		String query1 = "SELECT * FROM statcover_staff where staff_Id=" + id;

		ResultSet rs = ps.executeQuery(query1);
		while (rs.next()) {
		
			String spec=rs.getString("staff_specialty");
			String pos=rs.getString("staff_position");
			String affil=rs.getString("staff_affiliation");
			
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

	<form id="staffEdit" method="post" autocomplete="off"
		action="${pageContext.request.contextPath}/StatcoverServlets.Staff_Edit"
		onsubmit="return validate();">

		<fieldset class="field-set">
			<legend>Staff Details</legend>

			<div class="field-group">
				<!-- Staff ID should not be empty and should be numeric only -->
				<label for="staffid">Staff ID:</label> <input type="text"
					name="staffid" id="staffid" size="5" maxlength="12"
					value="<%=rs.getString("staff_Id")%>" readonly />
			</div>

			<div class="field-group">
				<!-- First name should not be empty and should be a string not a number -->
				<label for="firstname">First name:</label> <input type="text"
					name="firstname" id="firstname" size="10" maxlength="40"
					required="required" pattern="[A-Za-z]{2,}"
					title="two or more alphabets only"
					value="<%=rs.getString("staff_firstName")%>" />
			</div>

			<div class="field-group">
				<!-- Last name should not be empty and should be a string not a number -->
				<label for="familyname">Last Name:</label> <input type="text"
					name="lastname" id="lastname" size="10" maxlength="40"
					required="required" pattern="[A-Za-z]{2,}"
					title="two or more alphabets only"
					value="<%=rs.getString("staff_lastName")%>" />
			</div>

			<div class="field-group">
				<!-- Email should not be empty and should contain at least one @ -->
				<label for="email">Email:</label> <input type="text" name="email"
					id="email" size="10" maxlength="40" required="required"
					pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
					title="Valid email address only, must contain at least one @ and .com"
					value="<%=rs.getString("staff_email")%>" />
			</div>

			<div class="field-group">
				<!-- Contact number should not be empty and contain numeric only -->
				<label for="contactNum">Contact Number:</label> <input type="text"
					name="contactNum" id="contactNum" size="7" maxlength="12"
					required="required" pattern="(^[0][2][12579]{1})(\d{6,7}$)"
					title="valid contact numbers only"
					value="<%=rs.getString("staff_contactNum")%>" />
			</div>

			<div class="field-group">
				<!-- Address should not be empty and should be Alphanumeric -->
				<label for="address">Address:</label> <input type="text"
					name="address" id="address" size="20" maxlength="50"
					required="required" pattern=".{10,}" title="Six or more characters"
					value="<%=rs.getString("staff_address")%>" />
			</div>

			<div class="field-group">
				<!-- Choose one specialty from the list -->
				<label for="specialty">Specialty:</label> <select id="specialty"
					name="specialty">
					<option value="">Select One</option>
					<option <%=GlobalUtils.isSelected("ED", spec)%> value="ED">ED</option>
					<option <%=GlobalUtils.isSelected("General", spec)%> value="General">General</option>
					<option <%=GlobalUtils.isSelected("Medical", spec)%> value="Medical">Medical</option>
					<option <%=GlobalUtils.isSelected("Surgical", spec)%> value="Surgical">Surgical</option>
					<option <%=GlobalUtils.isSelected("Maternity North", spec)%> value="Maternity North">Maternity North</option>
					<option <%=GlobalUtils.isSelected("Maternity South", spec)%> value="Maternity South">Maternity South</option>
					<option <%=GlobalUtils.isSelected("Gastro", spec)%> value="Gastro">Gastro</option>
					<option <%=GlobalUtils.isSelected("Birthing Unit", spec)%> value="Birthing Unit">Birthing Unit</option>		
				</select>
			</div>

			<br>

			<div class="field-group">
				<!-- Choose one position from the list -->
				<label for="position">Position:</label> <select required
					id="position" name="position">
					<option value="">Select One</option>
					<option <%=GlobalUtils.isSelected("RN", pos)%> value="RN">RN</option>
					<option <%=GlobalUtils.isSelected("HCA", pos)%> value="HCA">HCA</option>
				</select>
			</div>

			<br>

			<div class="field-group">
				<!-- Affiliation should not be empty and should be a string not a number  -->
				<label for="affiliation">Affiliation:</label> <select required
					id="affiliation" name="affiliation">
					<option value="">Select One</option>
					<option <%=GlobalUtils.isSelected("MMH", affil)%> value="MMH">MMH</option>
				</select>
			</div>

			<br>

			<div class="field-group">
				<!-- License number should not be empty and should be numeric -->
				<label for="licenseNum">License Number:</label> <input type="text"
					name="licenseNum" id="licenseNum" size="20" maxlength="50"
					pattern="^(0|[1-9][0-9]*)$" title="Valid license number only"
					value="<%=rs.getString("staff_licenseNum")%>" />
			</div>

			<div class="field-group">
				<!-- License Expiry should not be empty and should be a valid date  -->
				<label for="datepicker">License Expiry:</label> <input type="text"
					name="licenseExpiry" id="datepicker" placeholder=" MM/DD/YYYY"
					value="<%=rs.getString("staff_licenseExpiry")%>" />
			</div>

			<div class="field-group">
				<!-- Password should not be empty and should be Alphanumeric -->
				<label for="password">Password:</label> <input type="password"
					name="password" id="password" size="20" maxlength="50"
					required="required" pattern=".{6,}" title="Six or more characters"
					value="<%=rs.getString("staff_password")%>" />
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
