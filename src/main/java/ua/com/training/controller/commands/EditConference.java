package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.RequestParamUtil;
import ua.com.training.model.ResourceEnum;
import ua.com.training.model.dto.ConferenceDTO;
import ua.com.training.model.dto.ReportDTO;
import ua.com.training.model.entity.User;
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
        long conferenceId = Long.parseLong(request.getParameter("conference"));
        ConferenceDTO conference =
                new ConferenceDTO(conferenceService.getConferenceById(conferenceId, "en_US"),
                        conferenceService.getConferenceById(conferenceId, "uk_UA"));

        request.setAttribute("possibleSpeakers", new UserService().getAllUsers(locale.toString()));
        request.setAttribute("conference", conference);
        request.setAttribute("currentPage", request.getParameter("currentPage"));
        request.setAttribute("recordsPerPage", request.getParameter("recordsPerPage"));


        if (Objects.isNull(request.getParameter("submitted"))) {
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
        conference.setTopicEn(request.getParameter("conference-name-en"));
        conference.setTopicUa(request.getParameter("conference-name-ua"));
        conference.setDateTime(LocalDateTime.parse(request.getParameter("conference-date-time")));
        conference.setLocationEn(request.getParameter("conference-location-en"));
        conference.setLocationEn(request.getParameter("conference-location-ua"));

        List<ReportDTO> reports = conference.getReports();
        for (int i = 0; i < reports.size(); i++) {
            long reportId = reports.get(i).getId();
            long speakerId = Long.parseLong(request.getParameter("report-speaker" + reportId));

            LocalDateTime reportDateTime = LocalDateTime.parse(request.getParameter("report-date-time" + reportId));

            if (reportDateTime.compareTo(conferenceDateTime) < 0) {
                request.setAttribute("conference", conference);
                request.setAttribute("earlierThanConference" + reportId, messages.getString("info.message.earlier.than.conference"));
                return PATH_BUNDLE.getString("page.edit.conference");
            }

            ifUserChangeRole(speakerId);
            reports.set(i, buildReportDTOFromRequest(request, reportId, reportDateTime));
            LOG.debug(Arrays.toString(reports.toArray()));
        }

        request.setAttribute("conference", conference);

        if (!requestParamUtil.nullReportParametersPresent(request, "0")) {
            LocalDateTime newReportDateTime = LocalDateTime.parse(request.getParameter("report-date-time0"));

            if (newReportDateTime.compareTo(conferenceDateTime) < 0) {
                request.setAttribute("report-name-en0", request.getParameter("report-name-en0"));
                request.setAttribute("report-name-ua0", request.getParameter("report-name-ua0"));
                request.setAttribute("report-date-time0", request.getParameter("report-date-time0"));
                request.setAttribute("earlierThanConference0", messages.getString("info.message.earlier.than.conference"));

                return PATH_BUNDLE.getString("page.edit.conference");
            }
            reportService.addNewReportToConference(conference.getId(), buildReportDTOFromRequest(request, 0, newReportDateTime));
        }
        conferenceService.update(conference);

        return "redirect:/"
                + request.getSession().getAttribute("role")
                + PATH_BUNDLE.getString("path.catalog")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&currentPage=" + request.getParameter("currentPage");
    }


    private ReportDTO buildReportDTOFromRequest(HttpServletRequest request, long reportId, LocalDateTime reportDateTime) {
        long speakerId = Long.parseLong(request.getParameter("report-speaker" + reportId));

        return new ReportDTO.Builder()
                .setId(reportId)
                .setTopicEn(request.getParameter("report-name-en" + reportId))
                .setTopicUa(request.getParameter("report-name-ua" + reportId))
                .setDateTime(reportDateTime)
                .setSpeakerId(Long.parseLong(request.getParameter("report-speaker" + reportId)))
                .setSpeakerNameEn(userService.getNameById(speakerId, "en_US"))
                .setSpeakerNameUa(userService.getNameById(speakerId, "uk_UA"))
                .setSpeakerSurnameEn(userService.getSurnameById(Long.parseLong(request.getParameter("report-speaker" + reportId)), "en_US"))
                .setSpeakerSurnameUa(userService.getSurnameById(Long.parseLong(request.getParameter("report-speaker" + reportId)), "uk_UA"))
                .build();
    }

    private void ifUserChangeRole(long speakerId) {
        if (userService.getUserRole(speakerId) == User.Role.USER) {
            userService.changeRole(speakerId, User.Role.SPEAKER);
        }
    }

}

