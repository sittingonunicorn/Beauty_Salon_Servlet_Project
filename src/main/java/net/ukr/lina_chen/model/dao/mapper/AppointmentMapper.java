package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class AppointmentMapper implements ObjectMapper<Appointment> {
    @Override
    public Appointment extractFromResultSet(ResultSet rs, Locale locale) throws SQLException {
        return Appointment.AppointmentBuilder.appointment()
                .withId(rs.getLong("appointment_id"))
                .withDate(rs.getDate("date").toLocalDate())
                .withTime(rs.getTime("time").toLocalTime())
                .withProvided(rs.getBoolean("provided"))
                .withBeautyService(new BeautyserviceMapper().extractFromResultSet(rs, locale))
                .withMaster(new MasterMapper().extractFromResultSet(rs, locale))
                .withUser(User.Builder.anUser()
                        .withId(rs.getLong("master_user_id"))
                        .withEmail(rs.getString("user_email"))
                        .withName(rs.getString("user_name"))
                        .build())
                .build();
    }

    @Override
    public Appointment makeUnique(Map<Long, Appointment> map, Appointment appointment) {
        map.putIfAbsent(appointment.getId(), appointment);
        return map.get(appointment.getId());
    }
}
