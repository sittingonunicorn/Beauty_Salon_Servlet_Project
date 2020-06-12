package net.ukr.lina_chen.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.ukr.lina_chen.controller.utility.IConstants.CSRF_TOKEN;
import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_ERROR_DIRECT;

public class CSRFFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(CSRFFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getMethod().equals("POST")) {
            String csrfToken = request.getParameter(CSRF_TOKEN);
            if (csrfToken == null) {
                logger.error("csrf token is missing");
                response.sendRedirect(REDIRECT_ERROR_DIRECT);
                return;
            } else if (!csrfToken.equals(request.getSession().getAttribute(CSRF_TOKEN))) {
                logger.error("csrf token doesn't match");
                response.sendRedirect(REDIRECT_ERROR_DIRECT);
                return;
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}

