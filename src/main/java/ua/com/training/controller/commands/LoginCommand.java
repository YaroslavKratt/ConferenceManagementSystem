package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ResourceManager;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;


public class LoginCommand implements Command {
    private final static Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.info("email:" + email + " password:" + password);
        Locale locale = request.getLocale();
        HttpSession session = request.getSession();
        System.out.println("Locale:" + locale);
        ResourceBundle messageBundle = ResourceManager.getBundle(ResourceManager.MESSAGES_BUNDLE_NAME, locale);


        if (email == null || password == null) {
            logger.info("Empty email or login");
            return ResourceManager.getProperty(pathBundle, "login.page.path");
        }

        if (!isGuest(request)) {
            logger.warn("Already logged in user tried to enter");
            return "redirect:/" + UserService.getRoleString(email);
        }
        if (!UserService.checkUserExist(email)) {
            logger.info("User" + email + " dose not exist");
            putMessageInRequest(request, "wrongEmail", messageBundle, "message.no.user.with.email");
            return ResourceManager.getProperty(pathBundle, "login.page.path");
        }

        if (UserService.checkPassword(email, password)) {
            dropSessionIfLoggedIn(request, email);
            logInUser(request, email, password);
            logger.info("User " + email + " signed in");
            return "redirect:/" + UserService.getRoleString(email);
        }
        else {
            logger.warn("Wrong password");

        }


        return pathBundle.getString("login.page.path");
    }


    private void logInUser(HttpServletRequest request, String email, String password) {
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("role", UserService.getUserRole(email).getRole());
        request.getSession().getServletContext().setAttribute(email, request.getSession());
    }

    private void putMessageInRequest(HttpServletRequest request, String wrongEmail, ResourceBundle messageBundle, String messageName) {
        request.setAttribute(wrongEmail, ResourceManager.getProperty(messageBundle, messageName));

    }

    private boolean isGuest(HttpServletRequest request) {
        return request.getSession().getAttribute("role").equals(User.Role.GUEST.getRole());
    }

    private void dropSessionIfLoggedIn(HttpServletRequest request, String email) {
        if (request.getSession().getServletContext().getAttribute(email) != null) {
            ((HttpSession) request.getSession().getServletContext().getAttribute(email)).invalidate();
        }
    }
}
