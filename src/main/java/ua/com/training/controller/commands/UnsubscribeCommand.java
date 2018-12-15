package ua.com.training.controller.commands;

import sun.net.ResourceManager;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.ResourceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class UnsubscribeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ReportService reportService = new ReportService();
        UserService userService = new UserService();
        ResourceBundle messageBundle = new ResourceService().getBundle(ResourceService.MESSAGE_BUNDLE, request.getLocale());
        long userId = userService.getUserId((String) session.getAttribute("email"));
        long reportId =  Long.valueOf(request.getParameter("reportForUnsubscription"));

        if(reportService.checkSubscription(userId, reportId)){
            reportService.unsubscribeUserFromReport(userId,reportId);
            request.setAttribute(String.valueOf(reportId), "unsubscribed");
            request.setAttribute("subscriptions", userService.getUserSubscriptionsIds(userId));


        }
        else {
            request.setAttribute("notSubscribed", messageBundle.getString("info.message.not.subscribed"));
        }
        return "redirect:/" + session.getAttribute("role") + PATH_BUNDLE.getString("path.catalog");

    }
}
