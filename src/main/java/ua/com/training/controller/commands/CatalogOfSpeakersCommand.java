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
    private SpeakerService speakerService = new SpeakerService();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);
        Map<String, Integer> paginationParameters = new PaginationUtil().calcPaginationParameters(request,
                speakerService.getSpeakersAmount());
        long userId = userService.getUserId((String) request.getSession().getAttribute("email"),locale.toLanguageTag());
        List<SpeakerDTO> speakers = speakerService.getPaginatedList(paginationParameters.get("begin"),
                paginationParameters.get("recordsPerPage"),locale.toLanguageTag());

        request.setAttribute("paginationParameters", paginationParameters);
        request.setAttribute("speakers", speakers);
        request.setAttribute("ratings", speakerService.getRatingMapFrom(speakers));

        if (Objects.isNull(request.getParameter("speakerId"))) {
            return PATH_BUNDLE.getString("page.speakers");
        }

        long speakerId = Long.parseLong(request.getParameter("speakerId"));
        if(speakerId==userId) {
            request.setAttribute("voteForYourself" + speakerId, messageBundle.getString("info.message.cant.vote.for.yourself"));
            return PATH_BUNDLE.getString("page.speakers");

        }

        userService.vote(userId, speakerId, Integer.valueOf(request.getParameter("rating" + speakerId)));
        return "redirect:/" + request.getSession().getAttribute("role") + PATH_BUNDLE.getString("path.speakers");
    }
}
