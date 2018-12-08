package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    private final static Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    public Command getCommand(HttpServletRequest request) {
        Command currentCommand = new DefaultCommand();
        LOGGER.info("command before replace " + request.getRequestURI());

        String command = request.getRequestURI().replaceAll(".*/guest*.|.*/admin*.|.*/speaker*.|.*/user*.|","");
        LOGGER.info("command after replace" + command);
        if (command.isEmpty()) {
            return currentCommand;
        }

        try {
            LOGGER.trace("The Command Value is: " + CommandEnum.valueOf(command.toUpperCase()));
            currentCommand = CommandEnum.valueOf(command.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("There is no such command: " + command);
        }
        LOGGER.info("the command is: " + currentCommand);
        return currentCommand;
    }
}
