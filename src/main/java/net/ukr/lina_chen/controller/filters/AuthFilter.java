package net.ukr.lina_chen.controller.filters;

import net.ukr.lina_chen.controller.utility.SecurityUtility;
import net.ukr.lina_chen.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_ERROR_DIRECT;
import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_LOGIN_PATH;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        SecurityUtility securityUtility = new SecurityUtility();
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute("role");
        String path = request.getRequestURL().toString();
        if (role == null) {
            role = securityUtility.setGuestRole(session);
        }
        if (securityUtility.isForbiddenRequest(path, role)) {
            if (role.equals(Role.GUEST)) {
                response.sendRedirect(REDIRECT_LOGIN_PATH);
                return;
            } else {
                response.sendRedirect(REDIRECT_ERROR_DIRECT);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
