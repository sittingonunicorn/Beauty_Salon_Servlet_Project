package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MasterMapper implements ObjectMapper<Master> {
    @Override
    public Master extractFromResultSet(ResultSet rs) throws SQLException {
        return Master.MasterBuilder.master()
                .withId(rs.getLong("master_id"))
                .withTimeBegin(rs.getTime("time_begin").toLocalTime())
                .withTimeEnd(rs.getTime("time_end").toLocalTime())
                .withUser(User.Builder.anUser()
                        .withId(rs.getLong("master_user_id"))
                        .withName(rs.getString("master_name"))
                        .withEmail(rs.getString("master_email"))
                        .build())
                .withProfession(new ProfessionMapper().extractFromResultSet(rs))
                .build();
    }

    @Override
    public Master makeUnique(Map<Long, Master> map, Master master) {
        map.putIfAbsent(master.getId(), master);
        return map.get(master.getId());
    }
}
