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
import java.util.*;

public class EditConference implements Command {
    private final static Logger LOG = LogManager.getLogger(EditConference.class);
    private UserService userService = new UserService();
    private ConferenceService conferenceService = new ConferenceService();
    private RequestParamUtil requestParamUtil = new RequestParamUtil();
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);
        Conference conference = new ConferenceService().getConferenceById(Long.parseLong(request.getParameter("conference")));

        request.setAttribute("possibleSpeakers", new UserService().getAllUsers());
        request.setAttribute("conference", conference);
        request.setAttribute("currentPage",request.getParameter("currentPage") );
        request.setAttribute("recordsPerPage",request.getParameter("recordsPerPage") );


        if (request.getParameter("submitted") == null) {
            LOG.trace("Not Submitted");
            return PATH_BUNDLE.getString("page.edit.conference");
        }

        String[] reportIds = request.getParameterValues("report-id");

        if (requestParamUtil.nullReportParametersPresent(request, reportIds) || requestParamUtil.nullConferenceParametersPresent(request)) {
            LOG.trace("Some parameter is null");

            return PATH_BUNDLE.getString("page.edit.conference");

        }

        LocalDateTime conferenceDateTime = LocalDateTime.parse(request.getParameter("conference-date-time"));
        LOG.trace("Checking date");

        if (conferenceDateTime.compareTo(LocalDateTime.now()) < 0) {
            request.setAttribute("wrongDate", messages.getString("info.message.early.date"));
            return PATH_BUNDLE.getString("page.edit.conference");
        }
        conference.setTopic(request.getParameter("conference-name"));
        conference.setDateTime(LocalDateTime.parse(request.getParameter("conference-date-time")));
        conference.setLocation(request.getParameter("conference-location"));
        List<Report> reports = conference.getReports();
        LOG.debug("conference before db:" + conference) ;
        for (int i = 0; i <  reports.size(); i++) {
            long reportId =  reports.get(i).getId();
            LOG.debug("report id "+reportId);
            LOG.debug("bug:" + request.getParameter("report-date-time" + reportId));
            LocalDateTime reportDateTime = LocalDateTime.parse(request.getParameter("report-date-time" + reportId));

            if (reportDateTime.compareTo(conferenceDateTime) < 0) {
                request.setAttribute("conference", conference);
                request.setAttribute("earlierThanConference" + reportId, messages.getString("info.message.earlier.than.conference"));
                return PATH_BUNDLE.getString("page.edit.conference");
            }
            reports.set(i, new Report.Builder()
                    .setId(reportId)
                    .setTopic(request.getParameter("report-name" + reportId))
                    .setDateTime(reportDateTime)
                    .setSpeakerId(Long.parseLong(request.getParameter("report-speaker" + reportId)))
                    .setSpeakerName(userService.getNameById(Long.parseLong(request.getParameter("report-speaker" + reportId))))
                    .setSpeakerSurname(userService.getSurnameById(Long.parseLong(request.getParameter("report-speaker" + reportId))))
                    .build());
            LOG.debug(Arrays.toString(reports.toArray()));
        }

        request.setAttribute("conference", conference);
       LOG.debug("null  present:" +requestParamUtil.nullReportParametersPresent(request, "-new"));
        if (!requestParamUtil.nullReportParametersPresent(request, "-new")) {
            if (LocalDateTime.parse(request.getParameter("report-date-time-new")).compareTo(conferenceDateTime) < 0) {
                request.setAttribute("report-name-new",request.getParameter("report-name-new"));
                request.setAttribute("report-date-time-new",request.getParameter("report-date-time-new"));
                request.setAttribute("earlierThanConference-new", messages.getString("info.message.earlier.than.conference"));
                return PATH_BUNDLE.getString("page.edit.conference");
            }
            reportService.addNewReportToConference(conference.getId(), new Report.Builder()
                    .setTopic(request.getParameter("report-name-new"))
                    .setDateTime(LocalDateTime.parse(request.getParameter("report-date-time-new")))
                    .setSpeakerId(Long.parseLong(request.getParameter("report-speaker-new")))
                    .build());
        }


        conferenceService.update(conference);

        return "redirect:/"
                + request.getSession().getAttribute("role")
                + PATH_BUNDLE.getString("path.catalog")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&currentPage=" + request.getParameter("currentPage");
    }

}

