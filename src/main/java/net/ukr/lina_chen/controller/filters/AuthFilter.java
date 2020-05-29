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
import java.util.*;

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
            Set<Role> guestRoles = new HashSet<>();
            guestRoles.add(Role.GUEST);
            session.setAttribute("roles", guestRoles);
            roles = guestRoles;
        }
        if (securityUtility.isForbiddenRequest(path, roles)) {
            response.sendRedirect("redirect:/error");
            return;
        }
//
//        logger.info(session);
//        logger.info(session.getAttribute("roles"));
//        logger.info(context.getAttribute("loggedUsers"));

        chain.doFilter(request, response);
    }

}
