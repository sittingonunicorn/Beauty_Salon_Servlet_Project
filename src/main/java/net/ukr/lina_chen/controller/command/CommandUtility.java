package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static net.ukr.lina_chen.controller.utility.IConstants.LOGGED_USERS;

class CommandUtility {
    private CommandUtility() {
    }

    static void setUserRole(HttpServletRequest request,
                            Role role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getSession().getServletContext();
        context.setAttribute("email", email);
        session.setAttribute("role", role);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String email) {
        HashSet<String> loggedUsers = getLoggedUsers(request);
        return loggedUsers.stream().anyMatch(email::equals);
    }

    static void userLogIn(HttpServletRequest request, String email){
        HashSet<String> loggedUsers = getLoggedUsers(request);
        loggedUsers.add(email);
        request.getSession().getServletContext()
                .setAttribute(LOGGED_USERS, loggedUsers);
    }

    static void removeUserFromSession(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        HashSet<String> loggedUsers = getLoggedUsers(request);
        loggedUsers.remove(user.getEmail());
        request.getSession().getServletContext()
                .setAttribute(LOGGED_USERS, loggedUsers);

    }

    private static HashSet<String> getLoggedUsers(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute(LOGGED_USERS);
        return loggedUsers;
    }

    static Locale geLocale(HttpServletRequest request) {
        return new Locale(Optional.ofNullable((String) request.getSession().getAttribute("lang")).orElse("en"));
    }
}
