package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.exceptions.TimeIsBusyException;
import net.ukr.lina_chen.model.entity.Appointment;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment> {

    List<Appointment> getMasterAppointments(Long masterId);

    void setProvided (Long appointmentId);

    Long create(Appointment entity) throws TimeIsBusyException, SQLException;

    List<Appointment> findMastersDailyAppointments (Long masterId, Date date);

    List<Date> findMastersAppointmentDates (Long masterId);

    List<Appointment> findUsersAppointments (Long userId);

}
