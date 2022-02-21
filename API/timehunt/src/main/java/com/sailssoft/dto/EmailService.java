package com.sailssoft.dto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Async
public class EmailService implements EmailSender{

	private final JavaMailSender mailSender;
	
	private final static Logger LOGGER =LoggerFactory.getLogger(EmailService.class);
	
	@Override
	@Async
	public void send(String to, String link) {
	  try {
		  MimeMessage mimeMessage = mailSender.createMimeMessage();
		  
		  MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
		  helper.setText(link);
		  helper.setTo(to);
		  helper.setSubject("confirm your email");
		  helper.setFrom("timerhunt123@gmail.com");
		  mailSender.send(mimeMessage);
	  }
	  catch(MessagingException ex) {
		 LOGGER.error("failed to send email",ex);
		 throw new IllegalStateException("failed to send mail");
	  }
	}

}
