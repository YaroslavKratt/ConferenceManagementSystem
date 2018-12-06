package ua.com.training.controller;

import ua.com.training.controller.commands.Command;
import ua.com.training.controller.commands.CommandFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.getCommand(request);

        String page = command.execute(request);
        if(request.getSession()!=null)
        {
           // System.out.println("SESSION:" +  request.getSession().getServletContext().getAttribute("TOM"));
        }
        if(page.contains("redirect:")) {
          //  System.out.println("PAGE WITH REDIRECT: " + page);
            response.sendRedirect(request.getContextPath() + page.replace("redirect:", ""));
        }
        else {
          //  System.out.println(page);
            request.getRequestDispatcher(page).forward(request,response);
        }


    }
}
