package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    String SQL_FIND_ALL = "select * from users";
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " where user_id = ?";
    String SQL_FIND_BY_EMAIL = SQL_FIND_ALL + " where email = ?";
    String SQL_REPLACE = "replace into users (email, name, name_ukr, password, role) values (?, ?, ?, ?, ?)";
    String SQL_DELETE = "delete from users where user_id = ?";

    Optional<User> findUserByEmail(String email);

}
