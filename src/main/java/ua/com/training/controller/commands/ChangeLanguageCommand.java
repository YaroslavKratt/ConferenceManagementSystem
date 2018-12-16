package ua.com.training.controller.commands;


import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("lang",request.getParameter("lang"));
        return "redirect:/";
    }
}
