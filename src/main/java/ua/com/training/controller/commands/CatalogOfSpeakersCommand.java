package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.ResourceEnum;
import ua.com.training.model.dto.SpeakerDTO;
import ua.com.training.model.services.SpeakerService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CatalogOfSpeakersCommand implements Command {
    private final static Logger LOG = LogManager.getLogger(CatalogOfSpeakersCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        SpeakerService speakerService = new SpeakerService();
        UserService userService = new UserService();
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String userEmail = (String) request.getSession().getAttribute("email");
        List<SpeakerDTO> speakers = speakerService.getAllSpeakersWithReports();
        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);


        request.setAttribute("speakers", speakers);
        request.setAttribute("ratings", speakerService.getRatingMapFrom(speakers));

        if (request.getParameter("speakerId") == null) {
            return PATH_BUNDLE.getString("page.speakers");
        }
        long speakerId = Long.parseLong(request.getParameter("speakerId"));
        if (userService.alreadyVoted(userEmail, speakerId)) {
            LOG.info("User voted already for this speaker  " + messageBundle.getString("info.message.already.voted"));
            Map<String, String> alreadyVotedForSpeaker = new HashMap<>();

            alreadyVotedForSpeaker.put(String.valueOf(speakerId),messageBundle.getString("info.message.already.voted"));
            request.setAttribute("alreadyVoted"+speakerId,messageBundle.getString("info.message.already.voted"));
            return PATH_BUNDLE.getString("page.speakers");

        }
        return PATH_BUNDLE.getString("page.speakers");
    }
}
