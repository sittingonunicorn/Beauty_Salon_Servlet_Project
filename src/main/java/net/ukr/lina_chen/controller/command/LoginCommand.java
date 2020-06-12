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
            return LOGIN_PAGE + ERROR_PARAM;
        }

        Optional<UserDTO> user = userService.getUserByEmailAndPassword(
                email, password, locale);
        if (user.isPresent()) {
            if (CommandUtility.checkUserIsLogged(request, email)) {
                return REDIRECT + ERROR_ALREADY_LOGGED;
            }
            CommandUtility.userLogIn(request, email);
            request.getSession().setAttribute("user", user.get());
            Role role = user.get().getRole();
            if (role.equals(Role.ADMIN)||role.equals(Role.MASTER)||role.equals(Role.USER)) {
                CommandUtility.setUserRole(request, user.get().getRole(), email);
                return REDIRECT+role.toString().toLowerCase();
            }
        }
        return REDIRECT_LOGIN + ERROR_PARAM;
    }
}

