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
function viewTables(){
	var selectBox =document.getElementById('tables');
	var userInput = selectBox.options[selectBox.selectedIndex].value;
	if (userInput == 'dm'){
		 window.location = "${pageContext.request.contextPath}/jsps/tables_dm.jsp"; 
	}else if (userInput == 'staff'){
		window.location = "${pageContext.request.contextPath}/jsps/tables_staff.jsp";
	}else if (userInput == 'reg'){
		window.location = "${pageContext.request.contextPath}/jsps/tables_registrations.jsp";
	}else if (userInput == 'req'){
		window.location = "${pageContext.request.contextPath}/jsps/tables_requests.jsp";
	}else if (userInput == 'avail'){
		window.location = "${pageContext.request.contextPath}/jsps/tables_availability.jsp";
	}else if (userInput == 'reqMessage'){
		window.location = "${pageContext.request.contextPath}/jsps/tables_request_messages.jsp";
	}else if (userInput == 'aMessage'){
		window.location = "${pageContext.request.contextPath}/jsps/tables_accept_messages.jsp";
	}else{
		window.location = "${pageContext.request.contextPath}/jsps/tables_confirmed_request.jsp";
	}
	return false;
}
</script>
<title>Administrator Services</title>
</head>
<body>

<img src="${pageContext.request.contextPath}/images/statCover.png"
		alt="STATCover Logo"
		style="float: center; width: 600px; height: 200px;">
<br>
<br>
<a href="${pageContext.request.contextPath}/jsps/home.jsp">Home</a>
<br>
<br>
	<label style="color: red">Welcome ${admin}.</label>
<br>
<br>

<form id="home" method="post" autocomplete="off">

		<fieldset class="field-set">
			<legend>View Tables Here</legend>
			
			<div class="field-group">
				<!-- Choose one position from the list -->
				<label for="tables">Retrievable Tables:</label> <select required
					id="tables" name="tables">
					<option value="dm">Duty Managers</option>
					<option value="staff">Staff</option>
					<option value="reg">Registrations</option>
					<option value="req">Requests</option>
					<option value="avail">Availabilities</option>
					<option value="reqMessage">Request Messages</option>
					<option value="aMessage">Acceptance Messages</option>
					<option value="conReq">Confirmed Requests</option>
				</select>
				<input type="submit" value="OK" onclick= "return viewTables();" />
			</div>
		</fieldset>
	</form>

<br> 
<br>If you choose to add a new duty manager, please
	<a href="${pageContext.request.contextPath}/jsps/dm_insert_form.jsp">Add DM here</a>.

<br>
<br> If you choose to add a new staff, please
	<a href="${pageContext.request.contextPath}/jsps/staff_insert_form.jsp">Add Staff here</a>.
	
</body>
</html>