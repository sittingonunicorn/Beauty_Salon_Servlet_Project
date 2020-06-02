package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class RolesMapper implements ObjectMapper<Role>{
    @Override
    public Role extractFromResultSet(ResultSet rs, Locale locale) throws SQLException {
        return Role.valueOf(rs.getString("role_name"));
    }

    @Override
    public Role makeUnique(Map<Long, Role> map, Role role) {
        return null;
    }
}
