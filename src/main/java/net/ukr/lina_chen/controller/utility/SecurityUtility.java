package net.ukr.lina_chen.controller.utility;

import net.ukr.lina_chen.model.entity.Role;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SecurityUtility {

    Map<Role, String> permissions = new HashMap<>();

    public SecurityUtility() {
        permissions.put(Role.ADMIN, "logout, admin");
        permissions.put(Role.MASTER, "logout, master");
        permissions.put(Role.USER, "logout, user");
        permissions.put(Role.GUEST, "login, registration, error, index");

    }

    public boolean isForbiddenRequest(String path, Role role) {
        Optional<String> pathDirection = Optional.ofNullable(permissions.get(role));
        return pathDirection.map(s -> Arrays.stream(s.split(", ")).noneMatch(path::contains)).orElse(true);
    }
}