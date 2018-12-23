package ua.com.training.controller.listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Locale;

public class LanguageListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (httpSessionBindingEvent.getName().equals("lang")) {
            String internalizationParameters = (String) httpSessionBindingEvent.getSession().getAttribute("lang");
            httpSessionBindingEvent.getSession().setAttribute("locale",
                    new Locale(internalizationParameters.substring(0, 2), internalizationParameters.substring(3, 5)));
        }
    }
}