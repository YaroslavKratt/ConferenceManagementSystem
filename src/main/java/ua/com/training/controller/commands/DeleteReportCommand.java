package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class DeleteReportCommand implements Command {
    private final static Logger LOG = LogManager.getLogger(DeleteReportCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        long id = Long.parseLong(request.getParameter("reportIdForRemoving"));
        String conferenceIdParameter = "";

        new ReportService().deleteReport(id);
        request.setAttribute("possibleSpeakers", new UserService().getAllUsers(locale.toString()));

        if (request.getParameter("uri").contains("editconference")) {
            conferenceIdParameter = "&conference=" + request.getParameter("conference");
        }
        return "redirect:" + request.getParameter("uri")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&currentPage=" + request.getParameter("currentPage")
                + "&scrollPosition=" + request.getParameter("scrollPosition")
                + conferenceIdParameter;
    }
}
