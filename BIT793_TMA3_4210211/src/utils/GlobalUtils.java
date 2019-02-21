package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class GlobalUtils {
	
	public static String defaultMySQLDateFormat="yyyy-MM-dd hh:mm:ss";
	public static String defaultMySQLDateFormatWithoutTime="yyyy-MM-dd";
	public static String systemEmailUsername="statcoversystem@gmail.com";
	public static String systemEmailPassword="STATCover310119";
	public static String systemEmailSMTPServer="smtp.gmail.com";
	public static String systemEmailSMTPPort="587";
	
	//to quote a string inserted in MySQL database
	public static String quotedString(String str) {
		return "'"+str+"'";
	}
	
	//to convert not null entry for dates
	public static String dateToStringQuoted(Date date) {
		if (date==null)
			return null;
		else {
			DateFormat df = new SimpleDateFormat(defaultMySQLDateFormat);
			return quotedString(df.format(date));
		}
	}
	
	//method for selected input in a dropdown box
	public static String isSelected(String str, String dbValue)
	{
		if (str.equals(dbValue)) 
			return "selected";
		else
			return "";
	}
	
	//method to return a yyyy-MM-dd hh:mm:ss format for date now 
	
	public static String dateToStringWithoutQuotes(Date date) 
	{
	        if (date==null)
	        	return "null";
	        else
	        {
	        	DateFormat dateFormat = new SimpleDateFormat(defaultMySQLDateFormat);  
	        	return dateFormat.format(date); 
	        }
	}
	
	
	//method to read all the lines in a text file and return the content as a string
	public static String readFromFile(String filePath) throws IOException
	{
		try
		{
			File file = new File(filePath); 
			  
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			  
			String line; 
			String allLines="";
			while ((line = br.readLine()) != null) 
			{
			     allLines+=System.lineSeparator()+line;
			} 
			br.close();
			return allLines;
		}
		catch (Exception e)
		{
			return "Error:"+e.getMessage();
		}
	}
	
	//method to get the domain address and port of the web application
	public static String getBaseUrl(HttpServletRequest request) {
	    String scheme = request.getScheme() + "://";
	    String serverName = request.getServerName();
	    String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
	    String contextPath = request.getContextPath();
	    return scheme + serverName + serverPort + contextPath;
	  }
	
	//method to provide the physical disk path of the draft email
	public static String getFilePathInWebContentDirectory(ServletContext sc,String relativeWebPath)
	{
		return sc.getRealPath(relativeWebPath);
	}
	
	//calculates date difference between date now and requested shift date
	public static long differenceBetweenDates(java.util.Date date1, java.util.Date date2, String inWhat)
	{
	    long diffInMillies = date1.getTime() - date2.getTime();
	    long diff=0;
	    if (inWhat=="inDays")
	        diff=TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    else
		if (inWhat=="inMinutes")
		    diff=TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    return diff;   	
	}
	
	//
	public static Date stringToDate(String str) 
	{
		try
		{
			DateFormat df = new SimpleDateFormat(defaultMySQLDateFormat);
			return df.parse(str);
		}
		catch (Exception ex){
			return null;
			
		}
	}
	
	//
	public static String dateToStringWithoutTimeAndQuotes(Date date) 
	{
		try
		{
			DateFormat df = new SimpleDateFormat(defaultMySQLDateFormatWithoutTime);
			return df.format(date);
		}
		catch (Exception ex){
			return null;
			
		}
	}
	
	public static JavaEmail getEmailObject()
	{
		try
		{
			return new JavaEmail(systemEmailSMTPServer, systemEmailSMTPPort, systemEmailUsername, systemEmailPassword);
			
		}
		catch (Exception ex) 
		{
			return null;
		}
	}
}
