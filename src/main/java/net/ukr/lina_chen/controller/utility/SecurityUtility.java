package net.ukr.lina_chen.controller.utility;

import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static net.ukr.lina_chen.controller.utility.PagesContainer.*;
import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_LOGIN;

public class SecurityUtility {

    private Map<Role, String> permissions = new HashMap<>();

    public SecurityUtility() {
        permissions.put(Role.ADMIN, "logout, admin, error, user");
        permissions.put(Role.MASTER, "logout, master, error, user");
        permissions.put(Role.USER, "logout, user, error");
        permissions.put(Role.GUEST, "login, registration, index, error");

    }

    public boolean isForbiddenRequest(String path, Set<Role> roles) {
        return permissions.entrySet()
                .stream()
                .filter(e -> roles.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .noneMatch(path::contains);
    }

    public String getRoleErrorRedirect(HttpServletRequest request){
        HttpSession session = request.getSession();
        Optional<UserDTO> user = Optional.ofNullable((UserDTO) session.getAttribute("user"));
        if (user.isPresent()) {
            if (user.get().getRoles().contains(Role.ADMIN)) {
                return REDIRECT_ADMIN;
            } else if (user.get().getRoles().contains(Role.MASTER)) {
                return REDIRECT_MASTER;
            }else {
                return REDIRECT_USER;
            }
        }
        return REDIRECT_LOGIN;
    }
}