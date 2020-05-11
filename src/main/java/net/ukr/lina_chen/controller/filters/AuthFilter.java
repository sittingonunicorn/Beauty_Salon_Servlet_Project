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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

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
        ServletContext context = request.getServletContext();
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable((String) session.getAttribute("lang"))
                        .orElse("en")));
        Role role = (Role) session.getAttribute("role");
        String path = request.getRequestURL().toString();
        if (role == null) {
            session.setAttribute("role", Role.GUEST);
            role = (Role) session.getAttribute("role");
        }
        if (securityUtility.isForbiddenRequest(path, role)) {
            request.setAttribute("unauthorized", bundle.getString("unauthorized.request"));
            response.sendRedirect("redirect:/error");
            return;
        }

//        logger.info(session);
//        logger.info(session.getAttribute("role"));
//        logger.info(context.getAttribute("loggedUsers"));

        chain.doFilter(req, res);
    }

}
