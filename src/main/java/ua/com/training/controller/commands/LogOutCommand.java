package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogOutCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            logger.info("User " + session.getAttribute("email") + " logged out");
            session.getServletContext().removeAttribute((String) session.getAttribute("email"));
            session.invalidate();
        }
        return "redirect:/" +  PATH_BUNDLE.getString("page.index.path");

    }
}
