package ua.com.training.controller.commands;

import ua.com.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command
{
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login == null || password == null) {
            return pathBundle.getString("login.page.path");
        }

        if(true){

            return "redirect:/" + User.Roles.USER.getRole();
        }
        return pathBundle.getString("login.page.path");
    }
}
