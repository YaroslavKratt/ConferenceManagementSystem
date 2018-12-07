package ua.com.training.controller.commands.utils;


import ua.com.training.model.services.ResourceManager;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ValidationUtil {
    private  ResourceBundle regexpBundle;

    ValidationUtil(Locale locale) {
        this.regexpBundle = new ResourceManager().getBundle(ResourceManager.REGEXP_BUNDLE,locale);
    }
    boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(regexpBundle.getString("email"), Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).find();
    }


    boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(regexpBundle.getString("password"), Pattern.CASE_INSENSITIVE);
        return pattern.matcher(password).find();
    }

}
