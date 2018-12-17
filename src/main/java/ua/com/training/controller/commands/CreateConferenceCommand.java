package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.ResourceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CreateConferenceCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CreateConferenceCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        {
            List<Report> reports = new ArrayList<>();
            Conference conference = new Conference();
            ResourceBundle messages = new ResourceService()
                    .getBundle(ResourceService.MESSAGE_BUNDLE, request.getLocale());
            UserService userService = new UserService();
            request.setAttribute("possibleSpeakers", new UserService().getAllUsers());

            if (request.getParameter("conference-name") == null) {
                return PATH_BUNDLE.getString("page.conference");
            }
            LocalDateTime conferenceDateTime = LocalDateTime.parse(request.getParameter("conference-date-time"));
            if (conferenceDateTime.compareTo(LocalDateTime.now()) < 0) {
                request.setAttribute("wrongDate", messages.getString("info.message.early.date"));
                return PATH_BUNDLE.getString("page.conference");
            }
            conference.setTopic(request.getParameter("conference-name"));
            conference.setLocation(request.getParameter("conference-location"));
            conference.setDateTime(conferenceDateTime);

            List<String> reportsTopic = Arrays.asList(request.getParameterValues("report-name"));
            List<String> reportsTime = Arrays.asList(request.getParameterValues("report-date-time"));
            List<String> reportsSpeaker = Arrays.asList(request.getParameterValues("report-speaker"));

            Iterator<String> topicsIterator = reportsTopic.iterator();
            Iterator<String> timesIterator = reportsTime.iterator();
            Iterator<String> speakersIterator = reportsSpeaker.iterator();

            while (topicsIterator.hasNext() && timesIterator.hasNext() && speakersIterator.hasNext()) {
                LocalDateTime reportDateTime = LocalDateTime.parse(timesIterator.next());
                long speakerId = Long.valueOf(speakersIterator.next());

                if (reportDateTime.compareTo(conferenceDateTime) < 0) {
                    request.setAttribute("erlierThanConference", messages.getString("info.message.earlier.than.conference"));
                    return PATH_BUNDLE.getString("page.conference");
                }
                if (userService.getUserRole(speakerId) == User.Role.USER) {
                    userService.changeRole(speakerId, User.Role.SPEAKER);
                }
                LOG.trace(userService.getUserRole(speakerId));
                reports.add(new Report.Builder()
                        .setTopic(topicsIterator.next())
                        .setDateTime(reportDateTime)
                        .setSpeakerId(speakerId)
                        .build());

            }

            conference.setReports(reports);
            if (!new ConferenceService().addConference(conference)) {
                LOG.error("Can't save new conference");
                throw new RuntimeException(messages.getString("error.message.not.saved"));
            }
             return "redirect:/" +  request.getSession().getAttribute("role") + PATH_BUNDLE.getString("path.catalog");
        }
    }


}

