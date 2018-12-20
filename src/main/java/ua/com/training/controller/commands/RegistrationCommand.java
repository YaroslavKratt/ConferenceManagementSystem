package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.SecurityUtil;
import ua.com.training.controller.utils.ValidationUtil;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ResourceService;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationCommand implements Command {
    private final static Logger LOG = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ValidationUtil validationUtil = new ValidationUtil();
        UserService userService = new UserService();
        Locale locale = Locale.forLanguageTag((String) request.getSession().getAttribute("lang"));
        ResourceBundle regexpBundle = new ResourceService().getBundle(ResourceService.REGEXP_BUNDLE, locale);
        ResourceBundle messageBundle = new ResourceService().getBundle(ResourceService.MESSAGE_BUNDLE, locale);

        if (isEmptyRequest(request)) {
            return PATH_BUNDLE.getString("page.registration");
        }
        String email = request.getParameter("email");

        if (userService.checkUserExist(email)) {
            request.setAttribute("wrongEmail", messageBundle.getString("info.message.user.already.exist"));
            return PATH_BUNDLE.getString("page.registration");
        }
        if (!validationUtil.validate(email, regexpBundle.getString("regexp.email"))) {
            request.setAttribute("wrongEmail", messageBundle.getString("info.message.wrong.email.input"));
            return PATH_BUNDLE.getString("page.registration");
        }
        String password = request.getParameter("mysql.password");
        if (!validationUtil.validate(password, regexpBundle.getString("regexp.password"))) {
            request.setAttribute("wrongPassword", messageBundle.getString("info.message.wrong.password.input"));
            return PATH_BUNDLE.getString("page.registration");

        }
        User user = new User.Builder().setName(request.getParameter("name"))
                .setSurname(request.getParameter("surname"))
                .setEmail(email)
                .setPassword(new SecurityUtil().hashPassword(password))
                .setRole(User.Role.USER)
                .build();

        userService.signUpUser(user);
        LOG.info("New user signed up");
        return "redirect:" + PATH_BUNDLE.getString("path.guest.login");
    }

    private boolean isEmptyRequest(HttpServletRequest request) {
        return !request.getParameterNames().hasMoreElements();
    }
}
