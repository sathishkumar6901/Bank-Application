package servlets;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSending {
	String otp = "";
	public String generateOTP() {
		UUID uuid = UUID.randomUUID();
		String pass = uuid.toString().replace("-", "");
		otp = pass.substring(pass.length() - 6);
		return otp;
	}

	public boolean sendMail(String email,String subject) {
		boolean test =false;
		
		String toEmail = email;
		String fromEmail = "sathish.skbank@gmail.com";
		String password= "nijzdtjsvxqgzeuv";
		try {
			Properties pr = new Properties();
			pr.setProperty("mail.smtp.host","smtp.gmail.com");
			pr.setProperty("mail.smtp.port", "587");
			pr.setProperty("mail.smtp.auth", "true");
			pr.setProperty("mail.smtp.starttls.enable", "true");
			pr.put("mail.smtp.tsl.trust","smtp.gmail.com");
			
			
			Session session =Session.getInstance(pr, new Authenticator(){
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail,password);
				}
			});
			
			Message mess = new MimeMessage(session);
			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			
			mess.setSubject(subject);
			
			mess.setText("Your One Time Password(OTP) is : "+otp );
			
			Transport.send(mess);
			
			test = true;
		}
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;
	}
}
