package ua.com.training.controller.commands;

import ua.com.training.model.ResourceEnum;
import ua.com.training.model.services.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteConferenceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messages = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);

        if (!new ConferenceService().deleteConference(Long.valueOf(request.getParameter("deleteConference")))) {
            throw new RuntimeException(messages.getString("error.message.conference.not.deleted"));
        }
        return "redirect:/"
                + request.getSession().getAttribute("role")
                + PATH_BUNDLE.getString("path.catalog")
                + "?recordsPerPage=" + request.getParameter("recordsPerPage")
                + "&currentPage=" + request.getParameter("currentPage");

    }
}
