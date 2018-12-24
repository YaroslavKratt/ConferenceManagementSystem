package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.PaginationUtil;
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
        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);
        Map<String, Integer> paginationParameters = new PaginationUtil().calcPaginationParameters(request,
                speakerService.getSpeakersAmount());
        long userId = userService.getUserId((String) request.getSession().getAttribute("email"));

        List<SpeakerDTO> speakers = speakerService.getPaginatedList(paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"));
        request.setAttribute("paginationParameters", paginationParameters);
        request.setAttribute("speakers", speakers);


        request.setAttribute("ratings", speakerService.getRatingMapFrom(speakers));

        if (request.getParameter("speakerId") == null) {
            return PATH_BUNDLE.getString("page.speakers");
        }
        long speakerId = Long.parseLong(request.getParameter("speakerId"));
        if(speakerId==userId) {
            request.setAttribute("alreadyVoted" + speakerId, messageBundle.getString("info.message.cant.vote.for.yourself"));
            return PATH_BUNDLE.getString("page.speakers");

        }
        if (userService.alreadyVoted(userId, speakerId)) {
            LOG.info("User voted already for this speaker  " + messageBundle.getString("info.message.already.voted"));
            request.setAttribute("alreadyVoted" + speakerId, messageBundle.getString("info.message.already.voted"));
            return PATH_BUNDLE.getString("page.speakers");

        }
        userService.vote(userId, speakerId, Double.valueOf(request.getParameter("rating" + speakerId)));
        return "redirect:/" + request.getSession().getAttribute("role") + PATH_BUNDLE.getString("path.speakers");
    }
}
