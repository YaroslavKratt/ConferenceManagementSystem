package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.PaginationUtil;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

public class CatalogCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CatalogCommand.class);
    private UserService userService = new UserService();
    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        Map<String, Integer> paginationParameters = new PaginationUtil().calcPaginationParameters(request,
                conferenceService.getConferencesAmount());
        request.setAttribute("paginationParameters", paginationParameters);
        request.setAttribute("conferences", conferenceService.getPaginatedList(paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"),locale.toString()));
        request.setAttribute("scrollPosition", request.getParameter("scrollPosition"));

        if (!isGuest(request)) {
            long userId = userService.getUserId((String) request.getSession().getAttribute("email"),locale.toString());
            request.setAttribute("userId", userId);
            request.setAttribute("subscriptions", userService.getUserSubscriptionsIds(userId));
        }

        return PATH_BUNDLE.getString("page.catalog");
    }


    private boolean isGuest(HttpServletRequest request) {
        return request.getSession().getAttribute("role").equals(User.Role.GUEST.getStringRole());
    }
}
