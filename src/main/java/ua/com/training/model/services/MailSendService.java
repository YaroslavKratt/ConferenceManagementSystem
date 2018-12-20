package ua.com.training.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailSendService implements Runnable {
    private Logger LOG = LogManager.getLogger(MailSendService.class);
    @Override
    public void run() {
        final String username = "all.conferences.mail@gmail.com";
        final String password = "15935755Zc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Transport transport = session.getTransport("smtp");
            transport.connect();
            Message message;
            String [] mails={"kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com","kratt202@gmail.com","wtf0100@gmail.com"};

            for (int i = 0; i < 2; i++)
            { message= new MimeMessage(session);
                message.setFrom(new InternetAddress("all.conferences.mail@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(mails[i]));
                message.setSubject("Message alert" + i);
                message.setText("Dear Yaroslav ,"
                        + "\n\n I`m testing my app");

                message.saveChanges();
                transport.send(message);
            }
            transport.close();
            LOG.info("Message Sent");

        } catch (MessagingException e) {
            LOG.info("Cant send");

            throw new RuntimeException(e);
        }

    }

    }


