package ua.com.training.model.services;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    public static final String SQL_REQUESTS_BUNDLE_NAME = "sql_requests";
    public static final String DB_UTILS_BUNDLE_NAME = "db_utils";
    public static final String PATHS_BUNDLE_NAME = "paths";
    public static final String MESSAGES_BUNDLE_NAME = "messages";
    public static final String REGEXP_BUNDLE = "regexp";

    public  ResourceBundle getBundle(String bundleName, Locale locale) {
        return ResourceBundle.getBundle(bundleName, locale);
    }

    public  ResourceBundle getBundle(String bundleName) {
        return ResourceBundle.getBundle(bundleName);
    }
}