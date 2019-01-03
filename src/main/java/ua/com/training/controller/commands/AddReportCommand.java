package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.RequestParamUtil;
import ua.com.training.model.ResourceEnum;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;
import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddReportCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(AddReportCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);
        Conference conference = new ConferenceService().getConferenceById(Long.parseLong(request.getParameter("conferenceId")));
        request.setAttribute("possibleSpeakers", new UserService().getAllUsers());
        request.setAttribute("conferenceId", request.getParameter("conferenceId"));
        request.setAttribute("recordsPerPage", request.getParameter("recordsPerPage"));
        request.setAttribute("currentPage", request.getParameter("currentPage"));
        request.setAttribute("scrollPosition",request.getParameter("scrollPosition"));

        if (request.getParameter("submitted") == null) {
            LOG.trace("Not Submitted");
            return PATH_BUNDLE.getString("page.add.report");
        }

        if (!new RequestParamUtil().nullReportParametersPresent(request, "-new")) {
            if (LocalDateTime.parse(request.getParameter("report-date-time-new")).compareTo(conference.getDateTime()) < 0) {
                request.setAttribute("report-name-new", request.getParameter("report-name-new"));
                request.setAttribute("report-date-time-new", request.getParameter("report-date-time-new"));
                request.setAttribute("earlierThanConference", messages.getString("info.message.earlier.than.conference"));
                return PATH_BUNDLE.getString("page.add.report");
            }
            new ReportService().addNewReportToConference(conference.getId(), new Report.Builder()
                    .setTopic(request.getParameter("report-name-new"))
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
