package ua.com.training.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.entity.User;
import ua.com.training.model.ResourceEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;


public class GuestFilter implements Filter {
    private final static Logger logger =  LogManager.getLogger(GuestFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        ResourceBundle pathBundle = ResourceBundle.getBundle(ResourceEnum.PATHS_BUNDLE.getBundleName());
        String role = (String) session.getAttribute("role");
        if (role==null) {
            session.setAttribute("role", User.Role.GUEST.getStringRole());
            logger.debug("PATH IN FILTER: " + request.getRequestURI());
            //request.getRequestDispatcher(pathBundle.getString("page.index")).forward(request,response);
            response.sendRedirect(pathBundle.getString("page.index"));
            return;
        }


        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
