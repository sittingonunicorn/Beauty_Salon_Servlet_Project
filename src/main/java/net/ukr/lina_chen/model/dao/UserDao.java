package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    String QUERY_FIND_ALL = "select * from users";
    String QUERY_FIND_BY_ID = QUERY_FIND_ALL + " where user_id = ?";
    String QUERY_FIND_BY_EMAIL = QUERY_FIND_ALL + " where email = ?";
    String QUERY_REPLACE = "replace into users (email, name, name_ukr, password, role) values (?, ?, ?, ?, ?)";
    String QUERY_DELETE = "delete from users where user_id = ?";

    Optional<User> findUserByEmail(String email);

}
