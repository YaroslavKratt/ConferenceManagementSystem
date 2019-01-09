package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.FilterSortUtil;
import ua.com.training.controller.utils.PaginationUtil;
import ua.com.training.model.services.conference_service.ConferenceService;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;
import ua.com.training.model.services.conference_service.FilterSortType;

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
        LOG.debug("type" + request.getParameter("sortType"));
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        FilterSortType filterSortType = new FilterSortUtil().setFilterSortType(request);
        Map<String, Integer> paginationParameters = new PaginationUtil()
                .calcPaginationParameters(request, conferenceService.getConferencesAmount(filterSortType));

        request.setAttribute("paginationParameters", paginationParameters);
        request.setAttribute("conferences", conferenceService.getSortedPaginatedConferences(filterSortType, paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"), locale.toString()));
        request.setAttribute("scrollPosition", request.getParameter("scrollPosition"));
        request.setAttribute("sortType", request.getParameter("sortType"));

        if (!Objects.isNull(request.getParameter("submitted"))) {
            setVisitorsAmountForAllReports(request);
        }

        long userId = userService.getUserId((String) request.getSession().getAttribute("email"), locale.toString());

        request.setAttribute("userId", userId);
        request.setAttribute("subscriptions", userService.getUserSubscriptionsIds(userId));
        request.setAttribute("conferences", conferenceService.getSortedPaginatedConferences(filterSortType, paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"), locale.toString()));
        return PATH_BUNDLE.getString("page.statistic");
    }



    private void setVisitorsAmountForAllReports(HttpServletRequest request) {
        for (String reportId : request.getParameterValues("report-id")) {
            reportService.setVisitorsAmount(Long.parseLong(reportId), Integer.parseInt(request.getParameter("visitors-amount" + reportId)));
        }
    }
}

