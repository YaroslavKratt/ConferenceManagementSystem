package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.PaginationUtil;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CatalogCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CatalogCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        ConferenceService conferenceService = new ConferenceService();
        Map<String, Integer> paginationParameters = new PaginationUtil().calcPaginationParameters(request,
                conferenceService.getConferencesAmount());
        request.setAttribute("paginationParameters", paginationParameters);
        request.setAttribute("conferences", conferenceService.getPaginatedList(paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage")));
        request.setAttribute("scrollPosition", request.getParameter("scrollPosition"));
        if (!isGuest(request)) {
            long userId = userService.getUserId((String) request.getSession().getAttribute("email"));
            request.setAttribute("userId", userId);
            request.setAttribute("subscriptions", userService.getUserSubscriptionsIds(userId));
        }
        return PATH_BUNDLE.getString("page.catalog");
    }

    private boolean isGuest(HttpServletRequest request) {
        return request.getSession().getAttribute("role").equals(User.Role.GUEST.getStringRole());
    }
}
