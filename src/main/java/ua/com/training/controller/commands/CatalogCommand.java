package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.PaginationUtil;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.conference_service.ConferenceService;
import ua.com.training.model.services.UserService;
import ua.com.training.model.services.conference_service.FilterSortType;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class CatalogCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CatalogCommand.class);
    private UserService userService = new UserService();
    private ConferenceService conferenceService = new ConferenceService();

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        FilterSortType filterSortType = setFilterSortType(request);
        Map<String, Integer> paginationParameters = new PaginationUtil()
                .calcPaginationParameters(request, conferenceService.getConferencesAmount(filterSortType));

        request.setAttribute("conferences", conferenceService.getSortedPaginatedConferences(filterSortType, paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"), locale.toString()));
        request.setAttribute("paginationParameters", paginationParameters);

        request.setAttribute("sortType", request.getParameter("sortType"));
        request.setAttribute("scrollPosition", request.getParameter("scrollPosition"));
        LOG.debug(conferenceService.getSortedPaginatedConferences(filterSortType, paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"), locale.toString()));
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

    private FilterSortType setFilterSortType(HttpServletRequest request) {
        FilterSortType filterSortType;
        if (Objects.isNull(request.getParameter("sortType"))) {
            filterSortType = FilterSortType.ALL;
        } else {
            filterSortType = FilterSortType.valueOf(request.getParameter("sortType").toUpperCase());
        }
        return filterSortType;
    }

}
