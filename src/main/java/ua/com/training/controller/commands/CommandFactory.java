package ua.com.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    private final static Logger log = LogManager.getLogger(CommandFactory.class);

    public Command getCommand(HttpServletRequest request) {
        Command currentCommand = new EmptyCommand();
        String command = request.getRequestURI().replaceAll(".*/guest/|.*/admin/|.*/speaker/|.*/user/","");
        System.out.println("COMMAND: " + command);
        if (command.isEmpty()) {
            return currentCommand;
        }

        try {
            currentCommand = CommandEnum.valueOf(command.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            log.warn("There is no such command: " + command);
        }
        return currentCommand;
    }
}
