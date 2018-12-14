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
        reportService.subscribeUserOnReport(userService.getUserId((String) session.getAttribute("email")),
                Long.valueOf(request.getParameter("reportForSubscription")));
        return session.getAttribute("role") + PATH_BUNDLE.getString("path.catalog");
    }
}
