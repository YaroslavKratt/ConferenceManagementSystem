package ua.com.training.controller.filters;

import ua.com.training.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.util.Enumeration;

public class GuestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role==null) {
            session.setAttribute("role", User.Roles.GUEST.getRole());
            //System.out.println("atrib in filter " + session.getAttribute("role"));
            request.getRequestDispatcher(request.getRequestURI() + User.Roles.GUEST.getRole()).forward(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
