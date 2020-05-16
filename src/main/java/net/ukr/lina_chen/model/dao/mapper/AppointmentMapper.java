package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AppointmentMapper implements ObjectMapper<Appointment> {
    @Override
    public Appointment extractFromResultSet(ResultSet rs) throws SQLException {
        return Appointment.AppointmentBuilder.appointment()
                .withId(rs.getLong("appointment_id"))
                .withDate(rs.getDate("date").toLocalDate())
                .withTime(rs.getTime("time").toLocalTime())
                .withProvided(rs.getBoolean("provided"))
                .withBeautyService(new BeautyserviceMapper().extractFromResultSet(rs))
                .withMaster(new MasterMapper().extractFromResultSet(rs))
                .withUser(new UserMapper().extractFromResultSet(rs))
                .build();
    }

    @Override
    public Appointment makeUnique(Map<Long, Appointment> map, Appointment appointment) {
        map.putIfAbsent(appointment.getId(), appointment);
        return map.get(appointment.getId());
    }
}
