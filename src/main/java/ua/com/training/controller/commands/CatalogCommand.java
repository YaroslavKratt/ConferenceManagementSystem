package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.services.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(CatalogCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        List<Conference> conferences = new ConferenceService().getAllConferences();
        request.setAttribute("conferences", conferences);
            return PATH_BUNDLE.getString("page.catalog");
    }
}
