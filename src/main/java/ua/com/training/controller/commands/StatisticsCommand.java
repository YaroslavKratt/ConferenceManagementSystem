package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.PaginationUtil;
import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class StatisticsCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(StatisticsCommand.class);
    private UserService userService = new UserService();
    private ConferenceService conferenceService = new ConferenceService();
    private ReportService reportService = new ReportService();

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        Map<String, Integer> paginationParameters = new PaginationUtil().calcPaginationParameters(request,
                conferenceService.getConferencesAmount());
        request.setAttribute("paginationParameters", paginationParameters);
        request.setAttribute("conferences", conferenceService.getPaginatedList(paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"),locale.toLanguageTag()));
        request.setAttribute("scrollPosition", request.getParameter("scrollPosition"));

        if (!Objects.isNull(request.getParameter("submitted"))) {
            setVisitorsAmountForAllReports(request);
        }

        long userId = userService.getUserId((String) request.getSession().getAttribute("email"),locale.toLanguageTag());
        request.setAttribute("userId", userId);
        request.setAttribute("subscriptions", userService.getUserSubscriptionsIds(userId));
        request.setAttribute("conferences", conferenceService.getPaginatedList(paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"),locale.toLanguageTag()));
        return PATH_BUNDLE.getString("page.statistic");
    }

    private void setVisitorsAmountForAllReports(HttpServletRequest request) {
        for (String reportId : request.getParameterValues("report-id")) {
            reportService.setVisitorsAmount(Long.parseLong(reportId), Integer.parseInt(request.getParameter("visitors-amount" + reportId)));
        }
    }
}

