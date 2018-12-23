package ua.com.training.controller.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Locale;

public class LanguageListener implements HttpSessionAttributeListener {
    private final static Logger LOG = LogManager.getLogger(LanguageListener.class);
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

        if (event.getName().equals("lang")) {
            LOG.trace("LISTENER LOCALE: " + String.valueOf( event.getSession().getAttribute("lang")));
            String internalizationParameters = String.valueOf(event.getSession().getAttribute("lang"));
            event.getSession().setAttribute("locale",
                    new Locale(internalizationParameters.substring(0, 2), internalizationParameters.substring(3, 5)));
        }
    }
}