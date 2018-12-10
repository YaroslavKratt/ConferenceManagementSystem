package ua.com.training.controller.commands;

import ua.com.training.model.services.ResourceService;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public interface Command {
     ResourceBundle PATH_BUNDLE = new ResourceService().getBundle(ResourceService.PATHS_BUNDLE_NAME);
    String execute(HttpServletRequest request);


}
