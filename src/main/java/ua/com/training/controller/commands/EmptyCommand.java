package ua.com.training.controller.commands;

import ua.com.training.model.services.Bundle;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        return pathBundle.getString("index.page.path");
    }
}
