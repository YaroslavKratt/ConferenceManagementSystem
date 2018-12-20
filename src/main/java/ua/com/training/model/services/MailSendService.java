package ua.com.training.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.dto.SubscriptionDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;


public class MailSendService implements Runnable {
    private final static Logger LOG = LogManager.getLogger(MailSendService.class);
    private final static ResourceBundle EMAIL_BUNDLE = new ResourceService().getBundle(ResourceService.EMAIL_BUNDLE);
    private final static ResourceBundle ACCESS = new ResourceService().getBundle(ResourceService.ACCESS_BUNDLE);
    private static ConferenceDao conferenceDao = DaoFactory.getInstance().createConferenceDao();

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
            List<Message> messages = buildMessages(session, conferenceDao.getAllSubscriptions());
            for (Message message : messages) {
                Transport.send(message);
            }

            transport.close();
            LOG.info("Message Sent");

        } catch (MessagingException e) {
            LOG.info("Cant send" + e);
            throw new RuntimeException(e);
        }

    }

    private List<Message> buildMessages(Session session, List<SubscriptionDTO> subscriptions) throws MessagingException {
        List<Message> messages = new ArrayList<>();
        for (SubscriptionDTO subscription : subscriptions) {
            LOG.trace("Date time is: " + subscription.getReportDateTime() + " is after 4 days from now:" + checkDate(subscription.getReportDateTime()));

            if (checkDate(subscription.getReportDateTime())) {
                messages.add(createMessage(session, subscription));
            }
        }
        return messages;

    }

    private Message createMessage(Session session, SubscriptionDTO subscription) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(EMAIL_BUNDLE.getString("app.email.address")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(subscription.getUserEmail()));
        message.setSubject(EMAIL_BUNDLE.getString("email.subject"));

        StringBuilder emailText = new StringBuilder()
                .append(EMAIL_BUNDLE.getString("email.text.start"))
                .append(subscription.getUserName() + " ")
                .append(subscription.getUserSurname())
                .append(EMAIL_BUNDLE.getString("email.text.why"))
                .append(EMAIL_BUNDLE.getString("email.text.conference"))
                .append(subscription.getConferenceTopic())
                .append(EMAIL_BUNDLE.getString("email.text.location"))
                .append(subscription.getConferenceLocation())
                .append(EMAIL_BUNDLE.getString("email.text.report.topic"))
                .append(subscription.getReportTopic())
                .append(EMAIL_BUNDLE.getString("email.text.thanks"));

        message.setText(emailText.toString());
        return message;
    }

    private boolean checkDate(LocalDateTime reportDateTime) {
        return DAYS.between(LocalDateTime.now(), reportDateTime) < Long.valueOf(EMAIL_BUNDLE.getString("notificate.before.days"));
    }


}


