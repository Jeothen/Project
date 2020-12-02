package com.ssafy.edu.model.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public void sendID(String mem_mail, String userID) {
		String host = "smtp.naver.com";
		final String user = "loucks@naver.com";
		final String password = "wndud135!!";

		String to = mem_mail;
		
		System.out.println(to);
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject
			message.setSubject("Awesomeplace 고객님의 아이디 입니다.");
			
			
			StringBuilder builder = new StringBuilder(userID);
			builder.setCharAt(2, '*');
			builder.setCharAt(3, '*');
			builder.setCharAt(4, '*');
			userID = builder.toString();
			// Text
			message.setText("보안상 아이디 일부가  * 처리 됩니다");
			message.setText("가입하신 아이디   :  " + userID);

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendPass(String mem_mail, String tmpPwd) {
		String host = "smtp.naver.com";
		final String user = "loucks@naver.com";
		final String password = "wndud135!!";

		String to = mem_mail;
		
		System.out.println(to);
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject
			message.setSubject("Awesomeplace 고객님의 임시 비밀번호 입니다.");
			
			
	
			// Text
			message.setText("반드시 개인정보에서 비밀번호를 변경해 주세요");
			message.setText("임시 비밀번호:  " + tmpPwd);

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}