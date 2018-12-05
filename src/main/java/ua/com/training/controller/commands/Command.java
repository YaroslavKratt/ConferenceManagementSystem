package ua.com.training.controller.commands;

import ua.com.training.model.services.Resources;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);
    ResourceBundle pathBundle = Resources.PATHS.getBundle();

}
