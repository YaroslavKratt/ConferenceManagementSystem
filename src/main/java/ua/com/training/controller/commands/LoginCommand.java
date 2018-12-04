package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.SecurityUtil;
import ua.com.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginCommand implements Command
{
    Logger logManager =  LogManager.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if(login == null || password == null) {
            return pathBundle.getString("login.page.path");
        }
        //todo
        if(!SecurityUtil.isLogedIn()){
            request.getSession().getServletContext().setAttribute(login, request.getSession());
            return "redirect:/" + User.Role.USER.getRole();
        }


        return pathBundle.getString("login.page.path");
    }
}
