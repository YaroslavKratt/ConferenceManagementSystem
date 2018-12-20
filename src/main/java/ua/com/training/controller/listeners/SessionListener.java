package ua.com.training.controller.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

    public class SessionListener implements HttpSessionListener {
        @Override
        public void sessionCreated(HttpSessionEvent httpSessionEvent) {
            httpSessionEvent.getSession().setAttribute("lang", new Locale("en", "US"));
        }

        @Override
        public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
            httpSessionEvent.getSession().getServletContext().removeAttribute(
                    (String) httpSessionEvent.getSession().getAttribute("email"));

        }
    }

