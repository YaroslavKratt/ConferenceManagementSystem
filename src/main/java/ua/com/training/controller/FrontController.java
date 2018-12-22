package ua.com.training.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.commands.Command;
import ua.com.training.controller.commands.CommandFactory;
import ua.com.training.controller.commands.DefaultCommand;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(FrontController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandFactory commandFactory = new CommandFactory();
        Command command;
        Optional<Command> commandOptional = commandFactory.getCommand(request, response);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if (commandOptional.isPresent()) {
            command = commandOptional.get();

        } else {
            LOG.warn("Moving to error page");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String page = command.execute(request);
        if (page.contains("redirect:")) {
            LOG.trace("page with redirect: " + page);
            LOG.trace("path after filtering: " + request.getContextPath() + page.replace("redirect:", ""));
            response.sendRedirect(request.getContextPath() + page.replace("redirect:", ""));
            return;
        } else {
            LOG.trace( "page" + page);
            request.getRequestDispatcher(page).forward(request,response);
        }


    }
}
