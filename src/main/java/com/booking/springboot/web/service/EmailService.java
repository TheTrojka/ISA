package com.booking.springboot.web.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

  private JavaMailSender mailSender;
  
  @Bean
  public JavaMailSender getJavaMailSender() {
      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      mailSender.setHost("smtp.gmail.com");
      mailSender.setPort(587);

      mailSender.setUsername("BookingSender@gmail.com");
      mailSender.setPassword("booking-password3");

      Properties props = mailSender.getJavaMailProperties();
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.debug", "true");

      return mailSender;
  }

  @Autowired
  public EmailService() {
    this.mailSender = getJavaMailSender();
  }
  
  @Async
  public void sendEmail(SimpleMailMessage email) throws MailException {
	  System.out.println("before");
    mailSender.send(email);
    System.out.println("after");
  }
}
