package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            Role role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("email", email);
        session.setAttribute("role", role);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String email) {
        HashSet<String> loggedUsers = getLoggedUsers(request);
        if (loggedUsers.stream().anyMatch(email::equals)) {
            return true;
        }
        loggedUsers.add(email);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    static void removeUserFromSession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        HashSet<String> loggedUsers = getLoggedUsers(request);
        loggedUsers.remove(user.getEmail());
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);

    }

    private static HashSet<String> getLoggedUsers(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers =(HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        return  loggedUsers;
    }
}
