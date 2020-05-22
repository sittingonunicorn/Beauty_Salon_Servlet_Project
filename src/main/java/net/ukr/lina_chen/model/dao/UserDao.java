package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    String QUERY_FIND_ALL = "select * from users left join users_role using (user_id) \n" +
            "left join role using (role_id)";
    String QUERY_FIND_BY_ID = QUERY_FIND_ALL + " where user_id = ?";
    String QUERY_FIND_BY_EMAIL = QUERY_FIND_ALL + " where email = ?";
    String QUERY_REPLACE_USER = "replace into users (email, name, name_ukr, password) values (?, ?, ?, ?)";
    String QUERY_REPLACE_USER_ROLE = "replace into users_role (user_id, role_id) values (?, 1)";
    String QUERY_DELETE = "delete from users where user_id = ?";
    String QUERY_GET_ID = "select user_id from users \n" +
            " where user_id = LAST_INSERT_ID()";

    Optional<User> findUserByEmail(String email);

}
