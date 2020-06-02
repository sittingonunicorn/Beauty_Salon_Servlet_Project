package net.ukr.lina_chen.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public interface ObjectMapper<T> {

    T extractFromResultSet(ResultSet rs, Locale locale) throws SQLException;

    T makeUnique(Map<Long, T> map,
                 T object);
}
