package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.SecurityUtil;
import ua.com.training.controller.utils.TransliteratorUtil;
import ua.com.training.controller.utils.ValidationUtil;
import ua.com.training.model.ResourceEnum;
import ua.com.training.model.dto.UserDTO;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class RegistrationCommand implements Command {
    private final static Logger LOG = LogManager.getLogger(RegistrationCommand.class);
    private ValidationUtil validationUtil = new ValidationUtil();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {

        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle regexpBundle = ResourceBundle.getBundle(ResourceEnum.REGEXP_BUNDLE.getBundleName(), locale);
        ResourceBundle messageBundle = ResourceBundle.getBundle(ResourceEnum.MESSAGE_BUNDLE.getBundleName(), locale);

        if (isEmptyRequest(request)) {
            return PATH_BUNDLE.getString("page.registration");
        }

        if (!validationUtil.validate(request.getParameter("name"), regexpBundle.getString("regex.name"))) {
            request.setAttribute("wrongName", messageBundle.getString("info.message.wrong.name"));
            return PATH_BUNDLE.getString("page.registration");
        }
        request.setAttribute("name", request.getParameter("name"));

        if (!validationUtil.validate(request.getParameter("surname"), regexpBundle.getString("regex.surname"))) {
            request.setAttribute("wrongSurname", messageBundle.getString("info.message.wrong.surname"));
            return PATH_BUNDLE.getString("page.registration");
        }
        request.setAttribute("surname", request.getParameter("surname"));

        String email = request.getParameter("email");

        if (userService.checkUserExist(email)) {
            request.setAttribute("wrongEmail", messageBundle.getString("info.message.user.already.exist"));
            return PATH_BUNDLE.getString("page.registration");
        }

        if (!validationUtil.validate(email, regexpBundle.getString("regexp.email"))) {
            request.setAttribute("wrongEmail", messageBundle.getString("info.message.wrong.email.input"));
            return PATH_BUNDLE.getString("page.registration");
        }
        request.setAttribute("email", email);

        String password = request.getParameter("password");
        if (!validationUtil.validate(password, regexpBundle.getString("regexp.password"))) {
            request.setAttribute("wrongPassword", messageBundle.getString("info.message.wrong.password.input"));
            return PATH_BUNDLE.getString("page.registration");

        }
        UserDTO user = new UserDTO.Builder()
                .setNameEn(transliterateIfUa(request.getParameter("name"),locale.toLanguageTag()).get("en"))
                .setNameUa(transliterateIfUa(request.getParameter("name"),locale.toLanguageTag()).get("en"))
                .setSurnameEn(request.getParameter("surname-en"))
                .setSurnameUa(request.getParameter("surname-ua"))
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
    private Map<String,String> transliterateIfUa(String name,String language) {
        Map<String,String> names = new HashMap<>();

        if(language.equals("uk_UA")) {
            names.put("en",  TransliteratorUtil.transliterateUatoEn(name));
            names.put("ua",  name);

        }
        else {
            names.put("en",  name);
            names.put("ua",  name);
        }
        return names;
    }
}
