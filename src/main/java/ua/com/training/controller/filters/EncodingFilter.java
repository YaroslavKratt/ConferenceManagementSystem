package ua.com.training.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.listeners.LanguageListener;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private final static Logger LOG = LogManager.getLogger(LanguageListener.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        LOG.trace("Encoding Filter");

        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
