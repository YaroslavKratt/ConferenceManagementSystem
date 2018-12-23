package ua.com.training.controller.commands;

import ua.com.training.model.entity.Speaker;
import ua.com.training.model.services.SpeakerService;

import javax.servlet.http.HttpServletRequest;

public class CatalogOfSpeakersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        SpeakerService speakerService = new SpeakerService();
        request.setAttribute("speakers", speakerService.getAllSpeakers());
        return PATH_BUNDLE.getString("page.speakers");
    }
}
