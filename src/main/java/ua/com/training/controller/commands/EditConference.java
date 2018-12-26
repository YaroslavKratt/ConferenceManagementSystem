package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.services.ConferenceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditConference implements Command {
    private final static Logger LOG = LogManager.getLogger(EditConference.class);
    @Override
    public String execute(HttpServletRequest request) {

       Conference conference=new ConferenceService().getConferenceById(Long.parseLong(request.getParameter("editConference")));
        request.setAttribute("possibleSpeakers", new UserService().getAllUsers());
        request.setAttribute("conference",conference);
        return PATH_BUNDLE.getString("page.edit.conference");
    }
}
