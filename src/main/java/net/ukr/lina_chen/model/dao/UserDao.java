package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.exceptions.UserExistsException;
import net.ukr.lina_chen.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findUserByEmail(String email);

    Long create(User entity) throws UserExistsException, SQLException;
}
