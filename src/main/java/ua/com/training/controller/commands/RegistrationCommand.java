package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.commands.utils.SecurityUtil;
import ua.com.training.controller.commands.utils.ValidationUtil;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ResourceManager;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class RegistrationCommand implements Command {
    private Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        ValidationUtil validationUtil = new ValidationUtil();
        UserService userService = new UserService();
        ResourceBundle regexpBundle = new ResourceManager().getBundle(ResourceManager.REGEXP_BUNDLE,request.getLocale());
        ResourceBundle messageBundle = new ResourceManager().getBundle(ResourceManager.MESSAGE_BUNDLE,request.getLocale());

        if(isEmptyRequest(request)){
            return PATH_BUNDLE.getString("page.registration.path");
        }
        if(userService.checkUserExist(request.getParameter("email"))) {
            request.setAttribute("wrongEmail", messageBundle.getString("user.already.exist") );
            return PATH_BUNDLE.getString("page.registration.path");
        }

        if(!validationUtil.validate(request.getParameter("email"),regexpBundle.getString("regexp.email"))){
            request.setAttribute("wrongEmail", messageBundle.getString("wrong.email.input") );

            return PATH_BUNDLE.getString("page.registration.path");

        }
        if(!validationUtil.validate(request.getParameter("password"),regexpBundle.getString("regexp.password"))){
            request.setAttribute("wrongPassword", messageBundle.getString("wrong.password.input") );
            return PATH_BUNDLE.getString("page.registration.path");

        }
        User user = new User.Builder().setName(request.getParameter("name"))
                .setSurname(request.getParameter("surname"))
                .setEmail(request.getParameter("email"))
                .setPassword(new SecurityUtil().hashPassword(request.getParameter("password")))
                .setRole(User.Role.USER)
                .build();

        userService.signUpUser(user);
        LOGGER.info("New user signed up");
         return "redirect:" + PATH_BUNDLE.getString("path.guest.login");
    }
    private boolean isEmptyRequest(HttpServletRequest request){
        return !request.getParameterNames().hasMoreElements();
    }
}
