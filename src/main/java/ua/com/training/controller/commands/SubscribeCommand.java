package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SubscribeCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(SubscribeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ReportService reportService = new ReportService();
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        long reportId = Long.valueOf(request.getParameter("reportForSubscription"));
        long userId = userService.getUserId((String) session.getAttribute("email"));

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
