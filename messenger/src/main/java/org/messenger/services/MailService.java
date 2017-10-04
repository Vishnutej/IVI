package org.messenger.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.messenger.data.EmailMessage;

public class MailService {

	public void sendEmail(EmailMessage emailMessage) {
		final String username = "testmail.9542@gmail.com";
		final String password = "test@P@ss";
		String toEmail = emailMessage.getEmailId();
		System.out.println("to email:"+toEmail);
		if(toEmail==null)
		{
			toEmail = "";
		}
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmail));
			message.setSubject("Your details!!");
			String newline =System.getProperty("line.separator"); 
			String emailBody = "Here are your details:" + newline
					+ "Campaign name: " + emailMessage.getCampaignName()
					+ newline + "List type:" + emailMessage.getListType()
					+ newline + "Youtube channel: "
					+ emailMessage.getYoutubeChannel() + newline + "Video: "
					+ emailMessage.getVideo() + newline + "List name: "
					+ emailMessage.getListName();
			
			message.setText(emailBody);

			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
