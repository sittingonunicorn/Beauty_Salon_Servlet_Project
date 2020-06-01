package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.exceptions.TimeIsBusyException;
import net.ukr.lina_chen.model.entity.Appointment;

import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment> {

    List<Appointment> getMasterAppointments(Long masterId);

    void setProvided (Long appointmentId);

    Long create(Appointment entity) throws TimeIsBusyException;

}
