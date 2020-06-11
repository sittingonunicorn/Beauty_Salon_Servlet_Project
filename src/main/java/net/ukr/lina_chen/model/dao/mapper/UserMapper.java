package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;


public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs, Locale locale) throws SQLException {
        return User.Builder.anUser()
                .withId(rs.getLong("user_id"))
                .withEmail(rs.getString("email"))
                .withName(rs.getString("name_"+locale.getLanguage()))
                .withPassword(rs.getString("password"))
                .withRole(Role.valueOf(rs.getString("role")))
                .build();
    }

    @Override
    public User makeUnique(Map<Long, User> map, User user) {
        map.putIfAbsent(user.getId(), user);
        return map.get(user.getId());
    }
}
