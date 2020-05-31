package net.ukr.lina_chen.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CSRFFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(CSRFFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getMethod().equals("POST")) {
            String csrfToken = request.getParameter("csrfToken");
            if (csrfToken == null) {
                logger.error("csrf token missing");
                response.sendRedirect(request.getContextPath() + "/app/error");
                return;
            } else if (!csrfToken.equals(request.getSession().getAttribute("csrfToken"))) {
                logger.error("csrf token doesn't match");
                response.sendRedirect(request.getContextPath() + "/app/error");
                return;
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}

