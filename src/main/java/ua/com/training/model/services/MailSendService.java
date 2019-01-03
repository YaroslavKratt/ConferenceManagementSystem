package ua.com.training.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.ResourceEnum;
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
    private final static ResourceBundle EMAIL_BUNDLE = ResourceBundle.getBundle(ResourceEnum.EMAIL_BUNDLE.getBundleName());
    private final static ResourceBundle ACCESS_BUNDLE = ResourceBundle.getBundle(ResourceEnum.ACCESS_BUNDLE.getBundleName());
    private static ConferenceDao conferenceDao = DaoFactory.getInstance().createConferenceDao();
    private static UserService userService = new UserService();
    private final String username = ACCESS_BUNDLE.getString("email.app.address");
    private final String password = ACCESS_BUNDLE.getString("email.password");

    @Override
    public void run() {


        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        LOG.trace("Authenticated");
        //sending emails in one session for better performance
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
        List<String> subscribedEmails = userService.getUserSubscribedEmails();
        List<Long> conferenceIds = conferenceDao.getAllConferenceIdsInSubscriptions();

        for (String email : subscribedEmails) {
            for (Long id : conferenceIds) {
                if (checkForAppropriateSubscription(email, id, subscriptions)) {
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

        if (!checkDate(subscriptions.get(0).getConferenceDateTime())) {
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
        LOG.trace("Starting to put reports in email");

        subscriptions.forEach(subscription -> {
            if (checkDate(subscription.getReportDateTime())) {
                emailText.append(EMAIL_BUNDLE.getString("email.text.report.date.time"))
                        .append(subscription.getReportDateTime().format(
                                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:MM")))
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


    private List<SubscriptionDTO> getAppropriateSubscriptions(String email, Long conferenceId, List<SubscriptionDTO> subscriptions) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getUserEmail().equals(email) && subscription.getConferenceId() == conferenceId)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    private boolean checkForAppropriateSubscription(String email, Long id, List<SubscriptionDTO> subscriptions) {
        return getAppropriateSubscriptions(email, id, subscriptions).size() > 0;
    }

}


