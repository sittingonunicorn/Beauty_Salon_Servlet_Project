package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findUserByEmail(String email);

}
