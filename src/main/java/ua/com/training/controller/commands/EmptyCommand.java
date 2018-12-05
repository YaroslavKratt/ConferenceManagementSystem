package ua.com.training.controller.commands;

import ua.com.training.model.services.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        return ResourceManager.getProperty(pathBundle,"index.page.path");
    }
}
