//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Service
public class MailServiceImp implements MailService {
	@Autowired
	public JavaMailSender mailSender;

	public MailServiceImp() {
	}

	public void sendSimpleMail(String sentTo, String subject, String message) {
		final String sentToMail = sentTo;
		final String subjectMail = subject;
		final String messageMail = message;
		final String username = "gip@itssolutions.co";
		final String password = "Pruebas";
		//final String password = "D6rHRsHQ#oOr";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "mail.itssolutions.co");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		final Properties properties = prop;
		CompletableFuture.runAsync(() -> {
			Session session = Session.getInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(username));
				mimeMessage.setRecipients(RecipientType.TO, InternetAddress.parse(sentToMail));
				mimeMessage.setSubject(subjectMail);
				mimeMessage.setContent(messageMail, "text/html; charset=utf-8");
				Transport.send(mimeMessage);
				System.out.println("Done");
			} catch (MessagingException var9) {

				System.out.println("ERROR SENDING MAIL: " + var9.getMessage());
				// var9.printStackTrace();
			}
		});

	}

	public void sendMailWithAttachment(String sentTo, String subject, String message)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		try {
			helper.setTo(sentTo);
			helper.setSubject(subject);
			helper.setText(message, true);
			this.mailSender.send(mimeMessage);
		} catch (Exception var7) {
			System.out.println("ERROR SENDING MAIL WITH ATTACHMENT: " + var7.getMessage());
		}

	}
}
