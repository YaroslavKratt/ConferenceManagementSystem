package ua.com.training.controller.commands;

import ua.com.training.model.services.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public interface Command {
    public ResourceBundle pathBundle = ResourceManager.getBundle(ResourceManager.PATHS_BUNDLE_NAME);
    String execute(HttpServletRequest request);


}
