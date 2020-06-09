package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.exceptions.TimeIsBusyException;
import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.mapper.AppointmentMapper;
import net.ukr.lina_chen.model.entity.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCAppointmentDao implements AppointmentDao {
    private static final Logger logger = LogManager.getLogger(JDBCAppointmentDao.class);
    private final Connection connection;
    private final AppointmentMapper appointmentMapper = new AppointmentMapper();
    private final Locale locale;

    public JDBCAppointmentDao(Connection connection, Locale locale) {
        this.connection = connection;
        this.locale = locale;
    }

    public Long create(Appointment entity) throws TimeIsBusyException {
        long appointmentId = 0L;
        try (PreparedStatement checkIfExists = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.appointments.by.master.date.time"), locale));
             PreparedStatement replace = connection.prepareStatement(
                     getLocalizedQuery(queryBundle.getString("query.replace.appointment"), locale));
             PreparedStatement getId = connection.prepareStatement(
                     getLocalizedQuery(queryBundle.getString("query.get.appointment.id"), locale))) {
            checkIfExists.setLong(1, entity.getMaster().getId());
            checkIfExists.setDate(2, Date.valueOf(entity.getDate()));
            checkIfExists.setTime(3, Time.valueOf(entity.getTime()));
            replace.setLong(1, entity.getMaster().getId());
            replace.setLong(2, entity.getUser().getId());
            replace.setLong(3, entity.getBeautyService().getId());
            replace.setTime(4, Time.valueOf(entity.getTime()));
            replace.setDate(5, Date.valueOf(entity.getDate()));
            replace.setBoolean(6, entity.isProvided());
            connection.setAutoCommit(false);
            try (ResultSet rs = checkIfExists.executeQuery()) {
                if (rs.next()) {
                    throw new TimeIsBusyException("Time is already busy");
                }
            }
            replace.executeUpdate();
            try (ResultSet resultSet = getId.executeQuery()) {
                if (resultSet.next()) {
                    appointmentId = resultSet.getLong("appointment_id");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        return appointmentId;
    }

    @Override
    public List<Appointment> findMastersDailyAppointments(Long masterId, Date date) {
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.appointments.by.master.date"), locale))) {
            ps.setLong(1, masterId);
            ps.setDate(2, date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = appointmentMapper.extractFromResultSet(rs, locale);
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return appointments;
    }

    @Override
    public List<Date> findMastersAppointmentDates(Long masterId) {
        List<Date> dates = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.all.masters.appointment.dates"), locale))){
            ps.setLong(1, masterId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Date date = rs.getDate("date");
                    dates.add(date);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return dates;

    }

    @Override
    public Appointment findById(Long id) {
        Appointment appointment = null;
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.appointment.by.id"), locale))) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    appointment = appointmentMapper.extractFromResultSet(rs, locale);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.all.appointments"), locale));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Appointment appointment = appointmentMapper.extractFromResultSet(rs, locale);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return appointments;
    }


    @Override
    public void update(Appointment entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.delete.appointment"), locale))) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<Appointment> getMasterAppointments(Long masterId) {
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.appointments.by.master"), locale))) {
            ps.setLong(1, masterId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = appointmentMapper.extractFromResultSet(rs, locale);
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return appointments;
    }

    @Override
    public void setProvided(Long appointmentId) {
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.update.appointment.provided"), locale))) {
            ps.setLong(1, appointmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}

