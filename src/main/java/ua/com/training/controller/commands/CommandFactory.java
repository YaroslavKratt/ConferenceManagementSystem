package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public class CommandFactory {
    private final static Logger LOG = LogManager.getLogger(CommandFactory.class);

    public Optional<Command> getCommand(HttpServletRequest request) {
        Command currentCommand = new DefaultCommand();
        String command = request.getRequestURI().replaceAll(".*/guest*.|.*/admin*.|.*/speaker*.|.*/user*.|", "");
        LOG.debug("Command is: " + command);

        if (command.isEmpty()) {
            return Optional.of(currentCommand);
        }
        try {
            currentCommand = CommandEnum.valueOf(command.toUpperCase()).getCommand();
            return Optional.of(currentCommand);
        } catch (IllegalArgumentException e) {
            LOG.warn("There is no such command: " + command);
            return Optional.empty();
        }
    }
}
