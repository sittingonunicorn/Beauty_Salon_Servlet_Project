package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) {
            return LOGIN_PAGE;
        }
        if (email.equals("") || password.equals("")) {
            return LOGIN_PAGE + "?error=true";
        }
        Optional<UserDTO> user = userService.getUserByEmailAndPassword(
                email, password, locale);
        if (user.isPresent()) {
            if (CommandUtility.checkUserIsLogged(request, email)) {
                return ERROR_PAGE;
            }
            request.getSession().setAttribute("user", user.get());
            if (user.get().getRoles().contains(Role.ADMIN)) {
                CommandUtility.setUserRoles(request, user.get().getRoles(), email);
                return REDIRECT_ADMIN;
            } else if (user.get().getRoles().contains(Role.MASTER)) {
                CommandUtility.setUserRoles(request, user.get().getRoles(), email);
                return REDIRECT_MASTER;
            } else if (user.get().getRoles().contains(Role.USER)) {
                CommandUtility.setUserRoles(request, user.get().getRoles(), email);
                return REDIRECT_USER;

            }
        }
        return REDIRECT_LOGIN + "?error=true";
    }
}

