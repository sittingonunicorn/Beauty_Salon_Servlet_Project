package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

public class ServiceTypeMapper implements  ObjectMapper<ServiceType> {
    @Override
    public ServiceType extractFromResultSet(ResultSet rs, Locale locale) throws SQLException {
        return ServiceType.ServiceTypeBuilder.serviceType()
                .withId(rs.getLong("service_type_id"))
                .withBeautyservicesType(rs.getString("beautyservices_type_"+locale.getLanguage()))
                .withMasters(new HashSet<>())
                .withBeautyServices(new HashSet<>())
                .build();
    }

    public ServiceType completeExtractFromResultSet (ResultSet rs, Locale locale) throws SQLException {
        return ServiceType.ServiceTypeBuilder.serviceType()
                .withId(rs.getLong("service_type_id"))
                .withBeautyservicesType(rs.getString("beautyservices_type_"+locale.getLanguage()))
                .withMasters(new HashSet<>())
                .withBeautyServices(new HashSet<>())
                .build();
    }

    @Override
    public ServiceType makeUnique(Map<Long, ServiceType> map, ServiceType serviceType) {
        map.putIfAbsent(serviceType.getId(), serviceType);
        return map.get(serviceType.getId());
    }
}
