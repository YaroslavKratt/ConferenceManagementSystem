package ua.com.training.model.services;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceService {
    public static final String SQL_REQUESTS_BUNDLE_NAME = "sql_requests";
    public static final String ACCESS_BUNDLE = "access";
    public static final String PATHS_BUNDLE_NAME = "paths";
    public static final String MESSAGE_BUNDLE = "messages";
    public static final String REGEXP_BUNDLE = "regexp";
    public static final String EMAIL_BUNDLE = "email";

    public  ResourceBundle getBundle(String bundleName, Locale locale) {
        return ResourceBundle.getBundle(bundleName, locale);
    }

    public  ResourceBundle getBundle(String bundleName) {
        return ResourceBundle.getBundle(bundleName);
    }
}