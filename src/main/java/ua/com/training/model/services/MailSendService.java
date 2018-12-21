package ua.com.training.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.dto.SubscriptionDTO;
import ua.com.training.model.exceptions.TooEarlyDateException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;


public class MailSendService implements Runnable {
    private final static Logger LOG = LogManager.getLogger(MailSendService.class);
    private final static ResourceBundle EMAIL_BUNDLE = new ResourceService().getBundle(ResourceService.EMAIL_BUNDLE);
    private static ConferenceDao conferenceDao = DaoFactory.getInstance().createConferenceDao();
    private static UserService userService = new UserService();

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
        LOG.trace("Authenticated");
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect();
            LOG.trace("Transport connected");

            List<Message> messages = buildMessages(session, conferenceDao.getSubscriptionsList());
            for (Message message : messages) {
                Transport.send(message);
            }

            transport.close();
            LOG.info("All messages have been sent");

        } catch (MessagingException e) {
            LOG.info("Cant send" + e);
            throw new RuntimeException(e);
        }

    }

    private List<Message> buildMessages(Session session, List<SubscriptionDTO> subscriptions) throws MessagingException {
        List<Message> messages = new ArrayList<>();
        List<String> subscriptedEmails = userService.getUserSubscriptedEmails();
        List<Long> conferenceIds = conferenceDao.getAllConferenceIdsInSubscriptions();

        for (String email : subscriptedEmails) {
            for (Long id : conferenceIds) {
                LOG.trace("check:" +checkForAppropriateSubscription(email,id,subscriptions) + " email=" + email+" id ="+ id);
                if(checkForAppropriateSubscription(email,id,subscriptions)) {
                    try {
                        messages.add(createMessage(session, email, getAppropriateSubscriptions(email, id, subscriptions)));
                    } catch (TooEarlyDateException ignored) {
                        LOG.trace("Too early date of conference to notificate");
                    }
                }
            }
        }

        return messages;

    }



    private Message createMessage(Session session, String userEmail, List<SubscriptionDTO> subscriptions) throws MessagingException, TooEarlyDateException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL_BUNDLE.getString("app.email.address")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
        message.setSubject(EMAIL_BUNDLE.getString("email.subject"));
        LOG.trace("subscriptions size " + subscriptions.size());

        if(!checkDate(subscriptions.get(0).getConferenceDateTime()))
        {
            throw new TooEarlyDateException();
        }

        StringBuilder emailText = new StringBuilder()
                .append(EMAIL_BUNDLE.getString("email.text.start"))
                .append(subscriptions.get(0).getUserName())
                .append(" ")
                .append(subscriptions.get(0).getUserSurname())
                .append(EMAIL_BUNDLE.getString("email.text.why"))
                .append(EMAIL_BUNDLE.getString("email.text.conference"))
                .append(subscriptions.get(0).getConferenceTopic())
                .append(EMAIL_BUNDLE.getString("email.text.location"))
                .append(subscriptions.get(0).getConferenceLocation())
                .append(EMAIL_BUNDLE.getString("email.text.reports"));
        LOG.trace("Starting puting reports in email");
        subscriptions.forEach(subscription -> {
            if (checkDate(subscription.getReportDateTime())) {
                emailText.append(EMAIL_BUNDLE.getString("email.text.report.date.time"))
                        .append(subscription.getReportDateTime().format(
                                DateTimeFormatter.ofPattern("dd LLLL yyyy HH:MM")))
                        .append(EMAIL_BUNDLE.getString("email.text.report.topic"))
                        .append(subscription.getReportTopic())
                        .append(EMAIL_BUNDLE.getString("email.text.speaker"))
                        .append(subscription.getSpeakerName())
                        .append(" ")
                        .append(subscription.getSpeakerSurname());
            }
        });
        emailText.append(EMAIL_BUNDLE.getString("email.text.thanks"));
        message.setText(emailText.toString());
        return message;
    }

    private boolean checkDate(LocalDateTime dateTime) {
        return DAYS.between(LocalDateTime.now(), dateTime) < Long.valueOf(EMAIL_BUNDLE.getString("notificate.before.days"));
    }


    private List<SubscriptionDTO> getAppropriateSubscriptions(String email, Long confernceId, List<SubscriptionDTO> subscriptions) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getUserEmail().equals(email)&&subscription.getConferenceId() == confernceId)
                .collect(Collectors.toCollection(ArrayList::new));


    }
    private boolean checkForAppropriateSubscription(String email, Long id, List<SubscriptionDTO> subscriptions) {
        return getAppropriateSubscriptions(email,id,subscriptions).size()>0;
    }

}


