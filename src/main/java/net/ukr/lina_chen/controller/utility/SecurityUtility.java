package net.ukr.lina_chen.controller.utility;

import net.ukr.lina_chen.model.entity.Role;

import java.util.*;

public class SecurityUtility {

    Map<Role, String> permissions = new HashMap<>();

    public SecurityUtility() {
        permissions.put(Role.ADMIN, "logout, admin");
        permissions.put(Role.MASTER, "logout, master");
        permissions.put(Role.USER, "logout, user");
        permissions.put(Role.GUEST, "login, registration, error, index");

    }

    public boolean isForbiddenRequest(String path, Set<Role> roles) {
        return permissions.entrySet()
                .stream()
                .filter(e -> roles.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .noneMatch(path::contains);
    }
}