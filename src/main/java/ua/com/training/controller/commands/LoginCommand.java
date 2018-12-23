package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ResourceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;


public class LoginCommand implements Command {
    private final static Logger LOG = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LOG.info("email:" + email + " password:" + password);
        Locale locale = (Locale)request.getSession().getAttribute("locale");
        UserService userService = new UserService();
        LOG.trace("Locale:" + locale);
        ResourceBundle messageBundle = new ResourceService().getBundle(ResourceService.MESSAGE_BUNDLE, locale);

        if (email == null || password == null) {
            LOG.info("Empty email or login");
            return PATH_BUNDLE.getString("page.login");
        }

        if (!isGuest(request)) {
            LOG.warn("Already logged in user tried to enter");
            return "redirect:/" + UserService.getRoleString(email);
        }
        if (!userService.checkUserExist(email)) {
            LOG.info("User" + email + " dose not exist");
            putMessageInRequest(request, "wrongEmail", messageBundle, "info.message.no.user.with.email");
            return PATH_BUNDLE.getString("page.login");
        }


        if (userService.checkPassword(email, password)) {
            dropSessionIfLoggedIn(request, email);
            logInUser(request, email, password);
            LOG.info("User " + email + " signed in");
            return "redirect:/" + UserService.getRoleString(email) + PATH_BUNDLE.getString("path.catalog");
        }
        else {
            putMessageInRequest(request, "wrongPassword", messageBundle, "info.message.wrong.password");
            LOG.warn("Wrong password");

        }


        return PATH_BUNDLE.getString("page.login");
    }


    private void logInUser(HttpServletRequest request, String email, String password) {
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("role", new UserService().getUserRole(email).getStringRole());
        request.getSession().getServletContext().setAttribute(email, request.getSession());
    }

    private void putMessageInRequest(HttpServletRequest request, String attribute, ResourceBundle messageBundle, String messageName) {
        request.setAttribute(attribute, messageBundle.getString(messageName));

    }

    private boolean isGuest(HttpServletRequest request) {
        return request.getSession().getAttribute("role").equals(User.Role.GUEST.getStringRole());
    }

    private void dropSessionIfLoggedIn(HttpServletRequest request, String email) {
        try {
            if (request.getSession().getServletContext().getAttribute(email) != null) {
                ((HttpSession) request.getSession().getServletContext().getAttribute(email)).invalidate();
            }
        } catch (IllegalStateException ise) {
            LOG.error("Session already invalidated, but email still in context: " + ise);
        }
           }
}
