package net.ukr.lina_chen.model.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static net.ukr.lina_chen.controller.utility.IConstants.*;


public class MailService {
    private String username;
    private String password;
    private String subject;
    private String text;

    private static Logger logger = LogManager.getLogger(MailService.class);

    public void sendEmail(String userEmail, Long appointmentId) {
        Properties mailProperties = new Properties();
        Properties sendingProperties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(MAIL_PROPERTIES);
             InputStream iss = getClass().getClassLoader().getResourceAsStream(MAIL_SENDING_PROPERTIES)) {
            mailProperties.load(is);
            sendingProperties.load(iss);
            username = sendingProperties.getProperty(USERNAME);
            password = sendingProperties.getProperty(PASSWORD);
            subject = sendingProperties.getProperty(SUBJECT);
            text = sendingProperties.getProperty(TEXT);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        Session session = Session.getInstance(mailProperties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEmail));
            message.setSubject(subject);
            message.setText(text + appointmentId);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
