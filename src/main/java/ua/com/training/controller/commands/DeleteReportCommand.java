package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.services.ReportService;

import javax.servlet.http.HttpServletRequest;

public class DeleteReportCommand implements Command {
    private final static Logger LOG = LogManager.getLogger(DeleteReportCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("reportIdForRemoving"));

        new ReportService().deleteReport(id);
        return "redirect:" + request.getParameter("uri")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&currentPage=" + request.getParameter("currentPage");
    }
}
