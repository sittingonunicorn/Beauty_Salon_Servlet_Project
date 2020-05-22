package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.entity.User;
import net.ukr.lina_chen.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.equals("") || password == null || password.equals("")) {
            return LOGIN_PAGE;
        }

        Optional<UserDTO> user = userService.getUserByEmailAndPassword(email, password, isLocaleEn(request));
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
        Set<Role> roles = new HashSet<>();
        roles.add(Role.GUEST);
        CommandUtility.setUserRoles(request, roles, email);
        return REDIRECT_LOGIN;
    }

    private boolean isLocaleEn(HttpServletRequest request) {
        String language = (String) request.getSession().getAttribute("lang");
        if (language!= null) {
            return new Locale(language).equals(Locale.ENGLISH);
        } else {
            return true;
        }
    }
}

