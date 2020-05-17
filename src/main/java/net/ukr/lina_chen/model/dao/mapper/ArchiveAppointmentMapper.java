package net.ukr.lina_chen.model.dao.mapper;

import net.ukr.lina_chen.model.entity.ArchiveAppointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ArchiveAppointmentMapper implements ObjectMapper<ArchiveAppointment> {
    @Override
    public ArchiveAppointment extractFromResultSet(ResultSet rs) throws SQLException {
        return new ArchiveAppointment(new AppointmentMapper().extractFromResultSet(rs),
                rs.getString("comment"));
    }

    @Override
    public ArchiveAppointment makeUnique(Map<Long, ArchiveAppointment> map,
                                         ArchiveAppointment archiveAppointment) {
        map.putIfAbsent(archiveAppointment.getId(), archiveAppointment);
        return map.get(archiveAppointment.getId());
    }
}
