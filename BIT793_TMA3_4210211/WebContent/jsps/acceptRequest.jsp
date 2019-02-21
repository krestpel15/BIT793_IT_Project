<%@page import="utils.JavaEmail"%>
<%@page import="java.util.Date"%>
<%@page import="utils.GlobalUtils"%>
<%@page import="dataAccessor.MySQLAccessor"%>
<%@page import="core.statCover.*"%>

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

<%
	String result="";
	staff _staff=null;
	requests requestObject=null;
	JavaEmail emailObject=GlobalUtils.getEmailObject();
	String ret="";

	try
	{
		//Get the parameters
		String guid=request.getParameter("guid");
		if (guid==null || guid.equals("")){
			result="No GUID is given!";
			out.println(result);
			return;
		}
	
	int staffid=-1;
	try
	{
		staffid=Integer.valueOf(request.getParameter("staffid"));
	}
	catch (Exception ex)
	{
	
	}
	
	if (staffid<=0)
	{
		result="No appropriate StaffID is given!";
		out.println(result);
		return;	
	}
	

	//Get the request object
	MySQLAccessor sa=new MySQLAccessor();
	
	//Get the staff who accepted the shift
	_staff=sa.getStaff(staffid);
	if (_staff==null)
	{
		result="StaffID is wrong!!";
		out.println(result);
		return;
	}
	
	
	//get the request object
	requestObject=sa.fetchTheRequestbyGUID(guid);
	if (requestObject==null)
	{
		result="No request found for this acceptance";
		out.println(result);
		return;
	}
	
	//Check the status of the request. If it is not 'created', write something about it
	if (!requestObject.getStatus().equals("created"))
	{
		result="The status("+requestObject.getStatus()+ ") of the request is not appropriate!";
		out.println(result);
		return;
	}
	//check the expiry date of the request. Give a message on the page to the user
	String datePart=GlobalUtils.dateToStringWithoutTimeAndQuotes(requestObject.getShiftDate());
	String timePart=requestObject.getTimeFrom();
	String wholeDateString=datePart+" "+timePart+":00";
	java.util.Date fromDate=GlobalUtils.stringToDate(wholeDateString);
	long diff=GlobalUtils.differenceBetweenDates(new java.util.Date(),fromDate,"inMinutes");
	
	if (diff>-120) //2 hours
	{
		result="The request was expired!";
		out.println(result);
		return;
	}
	
	
	//Check the staffid for for the request. Give a message
	if (sa.checkPreviousRecordWithTheSameStaff(requestObject.getRequestId(), staffid)>0)
	{
		result="You have already been booked for this request!";
		out.println(result);
		return;
	}
	
	
	//Check the quota for the request. Give a message
	if (sa.getAcceptanceCount(requestObject.getRequestId())>=requestObject.getQuantity())
	{
		result="The request has already reached the sufficient number of staff for the request!";
		out.println(result);
		return;
	}
	

	
	//get email of the duty manager
	dutyManager dm=sa.getDutyManager(requestObject.getDmId());
	if (dm==null)
	{
		result="Duty manager cannot found";
		out.println(result);
		return;
	}
	
	
	ServletContext sc=getServletContext();
	String filePath=GlobalUtils.getFilePathInWebContentDirectory(getServletContext(),"/htmls/requestConfirmation.html");
	String emailHTML=GlobalUtils.readFromFile(filePath);
	if (emailHTML.startsWith("Error"))
	{
		result="Your request could not be received! Error:Draft Email path not found!";
		out.println(result);
		return;
	}
	
	
	//send email to the duty manager
	//Replace the parameters in emailHTML
	

	if (emailObject==null)
	{
		result="Your request was inserted but we cannot send email to the duty manager because email service cannot be accessed";
		out.println(result);
		return;
	}
	
	try
	{
		emailHTML=emailHTML.replace("[DMName]", dm.getDmFname());
		emailHTML=emailHTML.replace("[DMSurname]", dm.getDmLname());
		emailHTML=emailHTML.replace("[StaffName]", _staff.getStaffFname());
		emailHTML=emailHTML.replace("[StaffSurname]", _staff.getStaffLname());
		emailHTML=emailHTML.replace("[Phone]", _staff.getStaffContactNum());
		emailHTML=emailHTML.replace("[Email]", _staff.getStaffEmail());
		emailHTML=emailHTML.replace("[Day]",GlobalUtils.dateToStringWithoutTimeAndQuotes(requestObject.getShiftDate()));
		emailHTML=emailHTML.replace("[Shift]",requestObject.getShift());
		emailHTML=emailHTML.replace("[From]",requestObject.getTimeFrom());
		emailHTML=emailHTML.replace("[To]",requestObject.getTimeTo());
		
		//Insert a record for Accept_Notification
		ret=sa.addAcceptNotification(_staff.getStaffId(), dm.getDmEmail(), emailHTML, requestObject.getRequestId());
		if (ret!="")
		{
			result="Your request could not be received. Error:"+ret;
			out.println(result);
			return;
		}
		emailObject.sendEmail(dm.getDmEmail(),"Request accepted",emailHTML);
	}	
	catch (Exception ex)
	{
		result="Your request was inserted but we cannot send email to the duty manager. Error:"+ex.getMessage();
		out.println(result);
		return;
	}
	
	//Insert a record for Confirmed_requests
	ret=sa.addConfirmedRequests(requestObject.getRequestId(), dm.getDmId(), _staff.getStaffId());
	if (ret!="")
	{
		result="Your request could not be received. Error:"+ret;
		out.println(result);
		return;
	}	

}
catch (Exception ex)
{
	result="There is a problem with your request. Error:"+ex.getMessage();
	out.println(result);
	return;	
}
finally
{
	if (result.equals(""))
		out.println("You were booked successfully.");
	else
		out.println("We could not book you.");
		
	if (_staff==null || requestObject==null || emailObject==null)
		return;
	
	out.println("You will get an email about this.");
	
	try
	{
		//send an answer to the user, positive or negative depending on the value of result variable.
		ServletContext sc=getServletContext();
		String filePath="";
		String emailHTML="";
		String _result="";
		
		_result=result==""?"Success":"Error";
		filePath=GlobalUtils.getFilePathInWebContentDirectory(getServletContext(),"/htmls/confirmation[Result].html");
		filePath=filePath.replace("[Result]",_result);
		emailHTML=GlobalUtils.readFromFile(filePath);
		if (emailHTML.startsWith("Error"))
		{
			out.println("The email cannot be sent to the user. Error:"+emailHTML);
			return;
		}	
		
		emailHTML=emailHTML.replace("[Name]", _staff.getStaffFname());
		emailHTML=emailHTML.replace("[Surname]", _staff.getStaffLname());
		emailHTML=emailHTML.replace("[Day]",GlobalUtils.dateToStringWithoutTimeAndQuotes(requestObject.getShiftDate()));
		emailHTML=emailHTML.replace("[Shift]",requestObject.getShift());
		emailHTML=emailHTML.replace("[From]",requestObject.getTimeFrom());
		emailHTML=emailHTML.replace("[To]",requestObject.getTimeTo());
		
		if (result!="")
			emailHTML=emailHTML.replace("[ErrorMessage]",result);
		
		emailObject.sendEmail(_staff.getStaffEmail(),"Your booking",emailHTML);
	}
	catch (Exception ex) 
	{
		out.println("Error in sending answer email to the user:"+ex.getMessage());
	}

}





%>

</body>
</html>