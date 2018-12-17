package ua.com.training.controller.commands;

import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.ResourceService;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class DeleteConferenceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ResourceBundle messages = new ResourceService().getBundle(ResourceService.MESSAGE_BUNDLE);
        if (!new ConferenceService().deleteConference(Long.valueOf(request.getParameter("deleteConference")))) {
            throw new RuntimeException(messages.getString("error.message.conference.not.deleted"));
        }
        return "redirect:/" + request.getSession().getAttribute("role") + PATH_BUNDLE.getString("path.catalog");

    }
}
