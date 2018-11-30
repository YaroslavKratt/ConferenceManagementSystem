package ua.com.training.controller.commands;

import ua.com.training.model.services.Bundle;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);
    ResourceBundle pathBundle = ResourceBundle.getBundle(Bundle.PATHS.getBundleName());

}
