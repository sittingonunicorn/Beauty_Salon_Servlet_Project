package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.mapper.AppointmentMapper;
import net.ukr.lina_chen.model.entity.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCAppointmentDao implements AppointmentDao {
    private static final Logger logger = LogManager.getLogger(JDBCAppointmentDao.class);
    private final Connection connection;
    private final AppointmentMapper appointmentMapper = new AppointmentMapper();

    public JDBCAppointmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(Appointment entity) throws SQLException {
        Long appointmentId = 0L;
        //TODO check availability
        try (PreparedStatement ps = connection.prepareStatement(QUERY_REPLACE);
             PreparedStatement getId = connection.prepareStatement(QUERY_GET_ID)) {
            ps.setLong(1, entity.getMaster().getId());
            ps.setLong(2, entity.getUser().getId());
            ps.setLong(3, entity.getBeautyService().getId());
            ps.setTime(4, Time.valueOf(entity.getTime()));
            ps.setDate(5, Date.valueOf(entity.getDate()));
            ps.setBoolean(6, entity.isProvided());
            connection.setAutoCommit(false);
            ps.executeUpdate();
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
    public Appointment findById(Long id) {
        Appointment appointment = null;
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    appointment = appointmentMapper.extractFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        Map<Long, Appointment> appointments = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Appointment appointment = appointmentMapper.extractFromResultSet(rs);
                appointmentMapper.makeUnique(appointments, appointment);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(appointments.values());
    }


    @Override
    public void update(Appointment entity) {

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_DELETE)) {
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> getMasterAppointments(Long masterId) {
        Map<Long, Appointment> appointments = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_BY_MASTER_ID)) {
            ps.setLong(1, masterId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = appointmentMapper.extractFromResultSet(rs);
                    appointmentMapper.makeUnique(appointments, appointment);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(appointments.values());
    }

    @Override
    public void setProvided(Long appointmentId) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE_SET_PROVIDED)) {
            ps.setLong(1, appointmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}

