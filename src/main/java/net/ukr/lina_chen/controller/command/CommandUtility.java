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

    static void setUserRoles(HttpServletRequest request,
                             Set<Role> roles, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("email", email);
        session.setAttribute("roles", roles);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String email) {
        HashSet<String> loggedUsers = getLoggedUsers(request);
        if (loggedUsers.stream().anyMatch(email::equals)) {
            return true;
        }
        loggedUsers.add(email);
        request.getSession().getServletContext()
                .setAttribute(LOGGED_USERS, loggedUsers);
        return false;
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
        HashSet<String> loggedUsers =(HashSet<String>) request.getSession().getServletContext()
                .getAttribute(LOGGED_USERS);
        return  loggedUsers;
    }

    static ResourceBundle getQueryBundle (Locale locale){
        return ResourceBundle.getBundle("queries", locale);
    }

    static ResourceBundle getMessageBundle (Locale locale){
        return ResourceBundle.getBundle("messages", locale);
    }

    static Locale geLocale(HttpServletRequest request){
        return new Locale(Optional.ofNullable((String) request.getSession().getAttribute("lang")).orElse("en"));
    }
}
