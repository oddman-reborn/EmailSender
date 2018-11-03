/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.email.javasendemail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Monirul Islam
 */
public class SendEmail {

    public static void main(String[] args) {
        
        String host = "smtp.gmail.com";
        String sender = "yourEmail@gmail.com";
        String senderPasw = "yourEmailPasw";
        String recipent = "recipient@gmail.com";

        String msg = "Testing email...";
        String msgSub = "Test";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, senderPasw);
            }
        });

        for (int i = 101; i < 111; i++) {
            try {
                Transport transport = session.getTransport();
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
                message.setSubject(msgSub + i);
                message.setText(msg + i);

                transport.connect();
                Transport.send(message);
                transport.close();

                System.out.println("message sent successfully...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }
    }

}
