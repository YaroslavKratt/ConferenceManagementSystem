package ua.com.training.controller.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ValidationUtil {
    private final static Logger LOGGER = LogManager.getLogger(ValidationUtil.class);
    private ResourceBundle regexpBundle;

    public boolean validate(String data, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        LOGGER.debug("pattern in validation: " + pattern);
        LOGGER.debug("data for validation: " + data);

        return pattern.matcher(data).find();
    }
}


