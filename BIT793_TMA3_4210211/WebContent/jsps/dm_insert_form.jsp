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

	<img src="${pageContext.request.contextPath}/images/statCover.png"
		alt="STATCover Logo"
		style="float: center; width: 600px; height: 200px;">
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/jsps/home.jsp">Home</a> | <a href="${pageContext.request.contextPath}/jsps/adminServices.jsp">Admin Services</a>
	<br>
	<br>

	<h3>Please complete your details</h3>
	
	<!--Show the message if any -->
	<label style="color: red">${message}</label>

	<!--Remove the message. We should do this otherwise the last message always appears on the page -->
	<%
		request.setAttribute("message", null);
	%>
	
	<form id="dm" method="post" autocomplete="off"
		action="${pageContext.request.contextPath}/StatcoverServlets.Dm_Insert">

		<fieldset class="field-set">
			<legend>Duty Manager Details</legend>

			<div class="field-group">
				<!-- Duty Manager ID should not be empty and should be numeric only -->
				<label for="dmid">Duty Manager ID:</label> <input type="text"
					name="dmid" id="dmid" size="5" maxlength="12"
					placeholder="--Leave duty manager id empty--" disabled />
			</div>

			<div class="field-group">
				<!-- First name should not be empty and should be a string not a number -->
				<label for="firstname">First name:</label> <input type="text"
					name="firstname" id="firstname" size="10" maxlength="40"
					required="required" pattern="[A-Za-z]{2,}"
					title="two or more alphabets only"
					placeholder="--Please enter first name--" />
			</div>

			<div class="field-group">
				<!-- Last name should not be empty and should be a string not a number -->
				<label for="lastname">Last Name:</label> <input type="text"
					name="lastname" id="lastname" size="10" maxlength="40"
					required="required" pattern="[A-Za-z]{2,}"
					title="two or more alphabets only"
					placeholder="--Please enter last name--" />
			</div>

			<div class="field-group">
				<!-- Affiliation should not be empty and should be a string not a number  -->
				<label for="affiliation">Affiliation:</label> 
				<select required id="affiliation" name="affiliation">
					<option value="">Select One</option>
					<option>MMH</option>
				</select>
			</div>

			<div class="field-group">
				<!-- Email should not be empty and should contain at least one @ -->
				<label for="email">Email:</label> <input type="text" name="email"
					id="email" size="10" maxlength="40" required="required"
					pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
					title="Valid email address only, must contain at least one @ and .com"
					placeholder="--Please enter email--" />
			</div>

			<div class="field-group">
				<!-- Contact number should not be empty and contain numeric only -->
				<label for="contactNum">Contact Number:</label> <input type="text"
					name="contactNum" id="contactNum" size="7" maxlength="12"
					required="required" pattern="(^[0][2][12579]{1})(\d{6,7}$)"
					title="valid contact numbers only"
					placeholder="--Please enter contact number--" />
			</div>

			<div class="field-group">
				<!-- Password should not be empty and should be Alphanumeric -->
				<label for="password">Password:</label> <input type="password"
					name="password" id="password" size="20" maxlength="50"
					required="required" pattern=".{6,}" title="Six or more characters"
					placeholder="--Please enter password--" />
			</div>
			
		</fieldset>

		<input type="submit" id="submit" value="Submit" />
	</form>
</body>
</html>