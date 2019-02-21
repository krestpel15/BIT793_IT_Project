<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/select.css"
		type="text/css">
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/home.css"
		type="text/css">
<meta charset="ISO-8859-1">

<script>

function logInAs(){
	var selectBox =document.getElementById('logAs');
	var userInput = selectBox.options[selectBox.selectedIndex].value;
	if (userInput == 'admin'){
		 window.location = "${pageContext.request.contextPath}/jsps/loginAdmin.jsp"; 
	}else if (userInput == 'dm'){
		window.location = "${pageContext.request.contextPath}/jsps/loginDm.jsp";
	}else {
		window.location = "${pageContext.request.contextPath}/jsps/loginStaff.jsp";
	}
	return false;
}
</script>

<title>Home Page</title>
</head>
<body>

	<img src="${pageContext.request.contextPath}/images/statCover.png"
		alt="STATCover Logo"
		style="float: center; width: 600px; height: 200px;">
	
	<br>
	<br>
			
		<form id="home" method="post" autocomplete="off">
		
		<fieldset class="field-set">
			<legend>Log in Here</legend>
			
			<div class="field-group">
				<!-- Choose one position from the list -->
				<label for="logAs">Login As (Choose One):</label> <select required
					id="logAs" name="logAs">
					<option value="admin">Administrator</option>
					<option value="dm">Duty Manager</option>
					<option value="staff">Staff</option>
				</select>
				<input type="submit" value="OK" onclick= "return logInAs();" />
			</div>
		</fieldset>
	</form>
	
	<br> If you are new user, please
	<a href="${pageContext.request.contextPath}/jsps/registration_form.jsp">Register here</a>.
</body>
</html>