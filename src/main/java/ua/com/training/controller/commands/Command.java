package ua.com.training.controller.commands;

import ua.com.training.model.ResourceEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public interface Command {
     ResourceBundle PATH_BUNDLE = ResourceBundle.getBundle(ResourceEnum.PATHS_BUNDLE.getBundleName());
    String execute(HttpServletRequest request);


}
