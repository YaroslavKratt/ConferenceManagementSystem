package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.PaginationUtil;
import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class StatisticsCommand implements Command {
    private UserService userService = new UserService();
    private ConferenceService conferenceService = new ConferenceService();
    private static final Logger LOG = LogManager.getLogger(StatisticsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        Map<String, Integer> paginationParameters = new PaginationUtil().calcPaginationParameters(request,
                conferenceService.getConferencesAmount());
        request.setAttribute("paginationParameters", paginationParameters);
        request.setAttribute("conferences", conferenceService.getPaginatedList(paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage")));
        request.setAttribute("scrollPosition", request.getParameter("scrollPosition"));
        LOG.debug("scroll pos: " +request.getParameter("scrollPosition"));
        LOG.debug("page: " +request.getParameter("currentPage"));

        long userId = userService.getUserId((String) request.getSession().getAttribute("email"));
        request.setAttribute("userId", userId);
        request.setAttribute("subscriptions", userService.getUserSubscriptionsIds(userId));

        return PATH_BUNDLE.getString("page.statistic");
    }
}

