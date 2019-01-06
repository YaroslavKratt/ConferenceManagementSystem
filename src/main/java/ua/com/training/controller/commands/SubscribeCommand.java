package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;


public class SubscribeCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(SubscribeCommand.class);
    private ReportService reportService = new ReportService();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        HttpSession session = request.getSession();
        long reportId = Long.valueOf(request.getParameter("reportForSubscription"));
        long userId = userService.getUserId((String) session.getAttribute("email"),locale.toString());

        reportService.subscribeUserOnReport(userId, reportId);
        request.setAttribute(String.valueOf(reportId), "subscribed");
        request.setAttribute("subscriptions", userService.getUserSubscriptionsIds(userId));
        return "redirect:/"
                + session.getAttribute("role")
                + PATH_BUNDLE.getString("path.catalog")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&currentPage=" + request.getParameter("currentPage")
                +"&scrollPosition="+request.getParameter("scrollPosition");

    }
}
