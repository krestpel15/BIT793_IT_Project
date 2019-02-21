<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css"
	type="text/css">
<meta charset="US-ASCII">
<title>Login Page</title>
</head>
<body>

	<img src="${pageContext.request.contextPath}/images/statCover.png"
		alt="STATCover Logo"
		style="float: center; width: 600px; height: 200px;">
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/jsps/home.jsp">Home</a>
	<br>

	<h3>Login with email and password</h3>
	
	<!--Show the message if any -->
	<label style="color: red">${message}</label>

	<!--Remove the message. We should do this otherwise the last message always appears on the page -->
	<%
		request.setAttribute("message", null);
	%>

	<form id="LoginStaff" method="post" autocomplete="off"
		action="${pageContext.request.contextPath}/StatcoverServlets.LoginCheck_staff">

		<fieldset class="field-set">
			<legend>Staff Login Details</legend>

			<div class="field-group">
				<!-- Email should not be empty -->
				<label for="email">Staff Email:</label> <input type="text"
					name="email" id="email" size="5" maxlength="40" required="required"
					pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$"
					title="Valid email address only, must contain at least one @ and .com"
					placeholder="--Please your email address--" />
			</div>

			<div class="field-group">
				<!-- Password should not be empty -->
				<label for="password">Password:</label> <input type="password"
					name="password" id="password" size="20" maxlength="50"
					required="required" pattern=".{6,}" title="Six or more characters"
					placeholder="--Please enter password--" />
			</div>

		</fieldset>

		<input type="submit" value="Login" />
	</form>

	<br> If you are new user, please
	<a href="${pageContext.request.contextPath}/jsps/registration_form.jsp">Register here</a>.
</body>
</html>
