package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaEmail {
	
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	private String emailHost; //SMTP host
	private String emailPort; 
	private String senderUsername; 
	private String senderPassword; 

	
	
	public JavaEmail (String emailHost, String emailPort, String senderUsername, String senderPassword)
	{
		this.emailHost=emailHost;
		this.emailPort=emailPort;
		this.senderUsername=senderUsername;
		this.senderPassword=senderPassword;

		
		setMailServerProperties();

	}
	
	public void sendEmail(String to, String emailSubject, String emailBody) throws AddressException, MessagingException
	{
		createEmailMessage(to, emailSubject, emailBody);
		_sendEmail();
	}
	

	private void setMailServerProperties() {

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	private void createEmailMessage(String to, String emailSubject, String emailBody) throws AddressException,
			MessagingException 
	{
		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));


		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");//for a html email
		//emailMessage.setText(emailBody);// for a text email

	}

	private void _sendEmail() throws AddressException, MessagingException {

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, senderUsername, senderPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}

}
