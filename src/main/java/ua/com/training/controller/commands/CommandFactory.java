package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    private final static Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    public Command getCommand(HttpServletRequest request) {
        Command currentCommand = new EmptyCommand();
        LOGGER.info("COMMAND BEFORE REPLACE " + request.getRequestURI());

        String command = request.getRequestURI().replaceAll(".*/guest*.|.*/admin*.|.*/speaker*.|.*/user*.","");
        LOGGER.info("COMMAND AFTER REPLACE " + command);
        if (command.isEmpty()) {
            return currentCommand;
        }

        try {
            currentCommand = CommandEnum.valueOf(command.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("There is no such command: " + command);
        }
        LOGGER.info("THE COMMAND IS: " + currentCommand);
        return currentCommand;
    }
}
