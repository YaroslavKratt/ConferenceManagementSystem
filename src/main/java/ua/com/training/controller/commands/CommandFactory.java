package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    private final static Logger logger = LogManager.getLogger(CommandFactory.class);

    public Command getCommand(HttpServletRequest request) {
        Command currentCommand = new EmptyCommand();
        logger.info("COMMAND BEFORE REPLACE " + request.getRequestURI());

        String command = request.getRequestURI().replaceAll(".*/guest*.|.*/admin*.|.*/speaker*.|.*/user*.","");
        logger.info("COMMAND AFTER REPLACE " + command);
        if (command.isEmpty()) {
            return currentCommand;
        }

        try {
            currentCommand = CommandEnum.valueOf(command.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            logger.warn("There is no such command: " + command);
        }
        logger.info("THE COMMAND IS: " + currentCommand);
        return currentCommand;
    }
}
