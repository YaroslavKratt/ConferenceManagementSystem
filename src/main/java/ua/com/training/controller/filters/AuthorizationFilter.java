package ua.com.training.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.utils.SecurityUtil;
import ua.com.training.model.entity.User;
import ua.com.training.model.exceptions.AccessDeniedException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public class AuthorizationFilter implements Filter {
    private final static Logger LOG = LogManager.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String userRole = (String) session.getAttribute("role");
        SecurityUtil utils = new SecurityUtil();

        if (utils.isSecuredPage(uri)) {
            boolean hasPermission = utils.checkPermission(uri, userRole);
            if (!hasPermission) {
                String email = (String) request.getSession().getAttribute("email");
                LOG.warn("Attempt of unauthorized access was caught , email: " + (email == null ? "user unknown" : email));
                throw new AccessDeniedException();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}