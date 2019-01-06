package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.ResourceEnum;
import ua.com.training.model.dto.ConferenceDTO;
import ua.com.training.model.dto.ReportDTO;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

public class CreateConferenceCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CreateConferenceCommand.class);
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        List<ReportDTO> reports = new ArrayList<>();
        ConferenceDTO conference = new ConferenceDTO();
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);
        request.setAttribute("possibleSpeakers", new UserService().getAllUsers(locale.toString()));

        if (request.getParameter("conference-name-en") == null || request.getParameter("conference-name-ua") == null) {
            return PATH_BUNDLE.getString("page.conference");
        }
        LocalDateTime conferenceDateTime = LocalDateTime.parse(request.getParameter("conference-date-time"));
        if (conferenceDateTime.compareTo(LocalDateTime.now()) < 0) {
            request.setAttribute("wrongDate", messages.getString("info.message.early.date"));
            return PATH_BUNDLE.getString("page.conference");
        }
        conference.setTopicEn(request.getParameter("conference-name-en"));
        conference.setTopicUa(request.getParameter("conference-name-ua"));
        conference.setLocationEn(request.getParameter("conference-location-en"));
        conference.setLocationUa(request.getParameter("conference-location-ua"));
        conference.setDateTime(conferenceDateTime);

        List<String> reportsTopicEn = Arrays.asList(request.getParameterValues("report-name-en"));
        List<String> reportsTopicUa = Arrays.asList(request.getParameterValues("report-name-ua"));
        List<String> reportsTime = Arrays.asList(request.getParameterValues("report-date-time"));
        List<String> reportsSpeaker = Arrays.asList(request.getParameterValues("report-speaker"));


        for (int i = 0; i < reportsTopicEn.size(); i++) {
            LocalDateTime reportDateTime = LocalDateTime.parse(reportsTime.get(i));
            long speakerId = Long.valueOf(reportsSpeaker.get(i));

            if (reportDateTime.compareTo(conferenceDateTime) < 0) {
                request.setAttribute("erlierThanConference", messages.getString("info.message.earlier.than.conference"));
                return PATH_BUNDLE.getString("page.conference");
            }

            ifUserChangeRole(speakerId);

            reports.add(new ReportDTO.Builder()
                    .setTopicEn(reportsTopicEn.get(i))
                    .setTopicUa(reportsTopicUa.get(i))
                    .setDateTime(reportDateTime)
                    .setSpeakerId(speakerId)
                    .build());

        }

        conference.setReports(reports);

        if (!new ConferenceService().addConference(conference)) {
            LOG.error("Can't save new conference");
            throw new RuntimeException(messages.getString("error.message.not.saved"));
        }
        return "redirect:/" + request.getSession().getAttribute("role") + PATH_BUNDLE.getString("path.catalog");
    }


    private void ifUserChangeRole(long speakerId) {
        if (userService.getUserRole(speakerId) == User.Role.USER) {
            userService.changeRole(speakerId, User.Role.SPEAKER);
        }
    }




}

