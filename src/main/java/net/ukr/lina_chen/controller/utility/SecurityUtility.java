package net.ukr.lina_chen.controller.utility;

import net.ukr.lina_chen.model.entity.Role;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.*;

public class SecurityUtility {
    private final SecureRandom secureRandom = new SecureRandom();
    private Map<Role, String> permissions = new EnumMap<>(Role.class);

    public SecurityUtility() {
        permissions.put(Role.ADMIN, "logout, admin, error, user");
        permissions.put(Role.MASTER, "logout, master, error, user");
        permissions.put(Role.USER, "logout, user, error");
        permissions.put(Role.GUEST, "login, registration, index, error");

    }

    public boolean isForbiddenRequest(String path, Role role) {
        return Optional.ofNullable(permissions.get(role))
                .map(s -> Arrays.stream(s.split(", "))
                        .noneMatch(path::contains)).orElse(true);
    }

    public String generateCSRFToken() {
        byte[] bytes = new byte[30];
        secureRandom.nextBytes(bytes);
        return DatatypeConverter.printHexBinary(bytes);
    }

    public Role setGuestRole(HttpSession session) {
        session.setAttribute("roles", Role.GUEST);
        return Role.GUEST;
    }
}