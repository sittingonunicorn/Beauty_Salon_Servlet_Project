package net.ukr.lina_chen.controller.listener;

import net.ukr.lina_chen.controller.utility.SecurityUtility;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

import static net.ukr.lina_chen.controller.utility.IConstants.*;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        SecurityUtility utility = new SecurityUtility();
        httpSessionEvent.getSession().setAttribute(CSRF_TOKEN, utility.generateCSRFToken());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute(LOGGED_USERS);
        String email = (String) session.getAttribute("email");
        loggedUsers.remove(email);
        httpSessionEvent.getSession().setAttribute(LOGGED_USERS, loggedUsers);
    }
}
