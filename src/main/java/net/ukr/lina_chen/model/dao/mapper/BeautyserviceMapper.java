package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.BeautyService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class BeautyserviceMapper implements ObjectMapper<BeautyService> {
    @Override
    public BeautyService extractFromResultSet(ResultSet rs, Locale locale) throws SQLException {
        return BeautyService.BeautyServiceBuilder.beautyService()
                .withId(rs.getLong("beautyservice_id"))
                .withName(rs.getString("beautyservice_name_"+locale.getLanguage()))
                .withPrice(rs.getBigDecimal("price"))
                .withProfession(new ProfessionMapper().extractFromResultSet(rs, locale))
                .build();
    }

    @Override
    public BeautyService makeUnique(Map<Long, BeautyService> map, BeautyService beautyService) {
        map.putIfAbsent(beautyService.getId(), beautyService);
        return map.get(beautyService.getId());
    }
}
