package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.ArchiveDao;
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
    public void create(Appointment entity) throws SQLException {
        //TODO check availability
        try (PreparedStatement ps = connection.prepareStatement(QUERY_REPLACE)) {
            ps.setLong(1, entity.getMaster().getId());
            ps.setLong(2, entity.getUser().getId());
            ps.setLong(3, entity.getBeautyService().getId());
            ps.setTime(4, Time.valueOf(entity.getTime()));
            ps.setDate(5, Date.valueOf(entity.getDate()));
            ps.setBoolean(6, entity.isProvided());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException();
        }

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
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_ALL)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public void close() {

    }

    @Override
    public List<Appointment> getMasterAppointments(Long masterId) {
        Map<Long, Appointment> appointments = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_BY_MASTER_ID)) {
            ps.setLong(1, masterId);
            try(ResultSet rs = ps.executeQuery()){
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
}
