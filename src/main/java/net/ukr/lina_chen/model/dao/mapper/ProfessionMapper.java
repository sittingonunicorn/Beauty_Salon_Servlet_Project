package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.Profession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;

public class ProfessionMapper implements  ObjectMapper<Profession> {
    @Override
    public Profession extractFromResultSet(ResultSet rs) throws SQLException {
        return Profession.ProfessionBuilder.profession()
                .withId(rs.getLong("profession_id"))
                .withBeautyservicesType(rs.getString("beautyservices_type"))
                //.withBeautyservicesTypeUkr(rs.getString("beautyservices_type_ukr"))
                .withName(rs.getString("profession_name"))
                //.withNameUkr(rs.getString("profession_name_ukr"))
                .withMasters(new HashSet<>())
                .build();
    }

    @Override
    public Profession makeUnique(Map<Long, Profession> map, Profession profession) {
        map.putIfAbsent(profession.getId(), profession);
        return map.get(profession.getId());
    }
}
