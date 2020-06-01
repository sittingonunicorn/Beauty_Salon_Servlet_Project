package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.ArchiveAppointment;

import java.util.List;

public interface ArchiveDao extends GenericDao<ArchiveAppointment> {

    List<ArchiveAppointment> getMasterComments(Long masterId);

    List<ArchiveAppointment> getUserArchiveAppointments(Long userId);

    void setComment (String comment, Long appointmentId);

    List<ArchiveAppointment> getAllComments();

    Long create(ArchiveAppointment entity);
}
