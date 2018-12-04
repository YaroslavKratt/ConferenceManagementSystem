package ua.com.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "redirect:/";
    }
}
