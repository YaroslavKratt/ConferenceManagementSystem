package ua.com.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        return  PATH_BUNDLE.getString("registration.page.path");
    }
}
