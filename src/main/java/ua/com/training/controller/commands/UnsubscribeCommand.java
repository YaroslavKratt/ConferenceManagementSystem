package ua.com.training.controller.commands;

import ua.com.training.model.ResourceEnum;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class UnsubscribeCommand implements Command {
    private ReportService reportService = new ReportService();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), request.getLocale());
        long userId = userService.getUserId((String) session.getAttribute("email"),locale.toString());

        if (Objects.isNull(request.getParameter("reportForUnsubscription"))) {
            return "redirect:/" + request.getSession().getAttribute("role") + PATH_BUNDLE.getString("path.catalog");
        }

        long reportId = Long.valueOf(request.getParameter("reportForUnsubscription"));

        if (reportService.isSubscribed(userId, reportId)) {
            reportService.unsubscribeUserFromReport(userId, reportId);
            request.setAttribute(String.valueOf(reportId), "unsubscribed");
            request.setAttribute("subscriptions", userService.getUserSubscriptionsIds(userId));
        } else {
            request.setAttribute("notSubscribed", messageBundle.getString("info.message.not.subscribed"));
        }
        return "redirect:/"
                + session.getAttribute("role")
                + PATH_BUNDLE.getString("path.catalog")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&currentPage=" + request.getParameter("currentPage")
                + "&scrollPosition=" + request.getParameter("scrollPosition")
                + "&sortType=" + (request.getParameter("sortType").isEmpty()?"all":request.getParameter("sortType"));


    }
}
