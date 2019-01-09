package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.ValidationUtil;
import ua.com.training.model.ResourceEnum;
import ua.com.training.model.dto.ReportDTO;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;
import ua.com.training.model.services.conference_service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddReportCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddReportCommand.class);
    private ValidationUtil validationUtil = new ValidationUtil();

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);
        Conference conference = new ConferenceService()
                .getConferenceById(Long.parseLong(request.getParameter("conferenceId")), locale.toString());

        request.setAttribute("possibleSpeakers", new UserService().getAllUsers(locale.toString()));
        request.setAttribute("conferenceId", request.getParameter("conferenceId"));
        request.setAttribute("recordsPerPage", request.getParameter("recordsPerPage"));
        request.setAttribute("currentPage", request.getParameter("currentPage"));
        request.setAttribute("scrollPosition", request.getParameter("scrollPosition"));

        if (request.getParameter("submitted") == null) {
            LOG.trace("Not Submitted");
            return PATH_BUNDLE.getString("page.add.report");
        }

        if (!validationUtil.nullReportParametersPresent(request, "-new")) {
            if (LocalDateTime.parse(request.getParameter("report-date-time-new")).compareTo(conference.getDateTime()) < 0) {
                request.setAttribute("report-name-en-new", request.getParameter("report-name-en-new"));
                request.setAttribute("report-name-ua-new", request.getParameter("report-name-ua-new"));

                request.setAttribute("report-date-time-new", request.getParameter("report-date-time-new"));
                request.setAttribute("earlierThanConference", messages.getString("info.message.earlier.than.conference"));
                return PATH_BUNDLE.getString("page.add.report");
            }
            new ReportService().addNewReportToConference(conference.getId(), new ReportDTO.Builder()
                    .setTopicEn(request.getParameter("report-name-en-new"))
                    .setTopicUa(request.getParameter("report-name-ua-new"))
                    .setDateTime(LocalDateTime.parse(request.getParameter("report-date-time-new")))
                    .setSpeakerId(Long.parseLong(request.getParameter("report-speaker-new")))
                    .build());
        }
        return "redirect:/"
                + request.getSession().getAttribute("role")
                + PATH_BUNDLE.getString("path.catalog")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&scrollPosition=" + request.getParameter("scrollPosition")
                + "&currentPage=" + request.getParameter("currentPage");
    }
}
