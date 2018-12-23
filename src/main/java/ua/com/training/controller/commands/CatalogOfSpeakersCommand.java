package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dto.SpeakerDTO;
import ua.com.training.model.services.SpeakerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CatalogOfSpeakersCommand implements Command {
    private final static Logger LOG = LogManager.getLogger(CatalogOfSpeakersCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        SpeakerService speakerService = new SpeakerService();
        List<SpeakerDTO> speakers =speakerService.getAllSpeakersWithReports();
        request.setAttribute("speakers",speakers );
        request.setAttribute("ratings", speakerService.getRatingMapFrom(speakers));
        return PATH_BUNDLE.getString("page.speakers");
    }
}
