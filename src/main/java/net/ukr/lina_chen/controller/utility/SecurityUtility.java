package net.ukr.lina_chen.controller.utility;

import net.ukr.lina_chen.model.entity.Role;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.*;

public class SecurityUtility {
    private final SecureRandom secureRandom = new SecureRandom();
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

    public String generateCSRFToken() {
        byte[] bytes = new byte[50];
        secureRandom.nextBytes(bytes);
        return DatatypeConverter.printHexBinary(bytes);
    }

    public Set<Role> setGuestRoles(HttpSession session) {
        Set<Role> guestRoles = new HashSet<>();
        guestRoles.add(Role.GUEST);
        session.setAttribute("roles", guestRoles);
        return guestRoles;
    }
}