package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<User> user = Optional.ofNullable((User) session.getAttribute("user"));
        if (user.isPresent()) {
            if (user.get().getRoles().equals(Role.ADMIN)) {
                return REDIRECT_ADMIN;
            } else if (user.get().getRoles().equals(Role.USER)) {
                return REDIRECT_USER;
            } else if (user.get().getRoles().equals(Role.MASTER)) {
                return REDIRECT_MASTER;
            }
        }
        return REDIRECT_LOGIN;
    }
}
