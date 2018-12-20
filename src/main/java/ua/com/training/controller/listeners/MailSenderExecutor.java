package ua.com.training.controller.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.services.MailSendService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MailSenderExecutor implements ServletContextListener {
    private ScheduledExecutorService executorService;
    private Logger LOG = LogManager.getLogger(MailSendService.class);
    @Override
    public void contextInitialized(ServletContextEvent event) {
        executorService = Executors.newSingleThreadScheduledExecutor();

        LOG.info("Sending emails...");
        executorService.scheduleAtFixedRate(new MailSendService(),0,30, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executorService.shutdownNow();
    }
}
