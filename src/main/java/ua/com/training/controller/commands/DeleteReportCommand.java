package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.services.ReportService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteReportCommand implements Command {
    private final static Logger LOG = LogManager.getLogger(DeleteReportCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("reportIdForRemoving"));
        String conferenceIdParameter = "";

        new ReportService().deleteReport(id);
        request.setAttribute("possibleSpeakers", new UserService().getAllUsers());

        if (request.getParameter("uri").contains("editconference")) {
            conferenceIdParameter = "&conference=" + request.getParameter("conference");
        }
        return "redirect:" + request.getParameter("uri")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&currentPage=" + request.getParameter("currentPage")
                + "&scrollPosition=" + request.getParameter("scrollPosition")
                +conferenceIdParameter;
    }
}
