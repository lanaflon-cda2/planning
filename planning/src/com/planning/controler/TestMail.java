/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planning.controler;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author genereux
 */
public class TestMail {
    
    public static void testMail() {
        // Recipient's email ID needs to be mentioned.
      String to = "GenereuxWilcoxALAHASSA@student.emi.ac.ma";

      // Sender's email ID needs to be mentioned
      String from = "wilofice@gmail.com";
      final String username = "wilofice@gmail.com";//change accordingly
      final String password = "15-A-7CB";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = System.getProperties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");
      //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      //props.put("mail.smtp.socketFactory.fallback", "25");
      props.put("mail.smtp.quitwait", "false");
      //System.out.println("ici1");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

      try {
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject("Testing Subject");
	
	   // Now set the actual message
	   message.setText("Hello, this is sample for to check send " +
		"email using JavaMailAPI ");

	   // Send message
           System.out.println("ici2");
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
    
   public static void main(String args[]) {
       System.out.println("before");
       testMail();
       System.out.println("after");
   }
}





