package net.ukr.lina_chen.controller.filters;

import net.ukr.lina_chen.controller.utility.SecurityUtility;
import net.ukr.lina_chen.model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        SecurityUtility securityUtility = new SecurityUtility();
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Set<Role> roles = (Set<Role>) session.getAttribute("roles");
        String path = request.getRequestURL().toString();
        if (roles == null) {
            roles = securityUtility.setGuestRoles(session);
        }
        if (securityUtility.isForbiddenRequest(path, roles)) {
            logger.warn("unauthorized access attempt");
            if (roles.contains(Role.GUEST)) {
                response.sendRedirect(request.getContextPath() + "/app/login");
                return;
            } else {
                response.sendRedirect(request.getContextPath() + "/app/error");
                return;
            }
        }
        chain.doFilter(request, response);
    }

}
