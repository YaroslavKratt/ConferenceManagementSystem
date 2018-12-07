package ua.com.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        return PATH_BUNDLE.getString("index.page.path");
    }
}
