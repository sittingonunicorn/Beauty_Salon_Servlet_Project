package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.mapper.ArchiveAppointmentMapper;
import net.ukr.lina_chen.model.entity.ArchiveAppointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCArchiveDao implements ArchiveDao {

    private static final Logger logger = LogManager.getLogger(JDBCArchiveDao.class);
    private final Connection connection;
    private final ArchiveAppointmentMapper archiveMapper = new ArchiveAppointmentMapper();


    public JDBCArchiveDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(ArchiveAppointment entity) throws SQLException {
        Long archiveId = 0L;
        try (PreparedStatement replace = connection.prepareStatement(QUERY_REPLACE);
             PreparedStatement getId = connection.prepareStatement(QUERY_GET_ID)) {
            replace.setLong(1, entity.getMaster().getId());
            replace.setLong(2, entity.getUser().getId());
            replace.setLong(3, entity.getBeautyService().getId());
            replace.setTime(4, Time.valueOf(entity.getTime()));
            replace.setDate(5, Date.valueOf(entity.getDate()));
            replace.setBoolean(6, entity.isProvided());
            replace.setString(7, entity.getComment());
            connection.setAutoCommit(false);
            replace.executeUpdate();
            try (ResultSet resultSet = getId.executeQuery()) {
                if (resultSet.next()) {
                    archiveId = resultSet.getLong("appointment_id");
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
        return archiveId;
    }

    @Override
    public ArchiveAppointment findById(Long id) {
        ArchiveAppointment appointment = null;
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_BY_ID)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    appointment = archiveMapper.extractFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<ArchiveAppointment> findAll() {
        Map<Long, ArchiveAppointment> appointments = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
              ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                archiveMapper.makeUnique(appointments, appointment);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(appointments.values());
    }

    @Override
    public List<ArchiveAppointment> getAllComments() {
        Map<Long, ArchiveAppointment> appointments = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_ALL_COMMENTS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                archiveMapper.makeUnique(appointments, appointment);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(appointments.values());
    }

    @Override
    public void update(ArchiveAppointment entity) {

    }

    @Override
    public void delete(Long id) {

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
    public List<ArchiveAppointment> getMasterComments(Long masterId) {
        Map<Long, ArchiveAppointment> archiveAppointments = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_COMMENTS_BY_MASTER_ID)) {
            ps.setLong(1, masterId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                    archiveMapper.makeUnique(archiveAppointments, appointment);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>(archiveAppointments.values());
    }

    @Override
    public List<ArchiveAppointment> getUserArchiveAppointments(Long userId) {
        Map<Long, ArchiveAppointment> archiveAppointments = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_BY_USER_ID)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                    archiveMapper.makeUnique(archiveAppointments, appointment);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>(archiveAppointments.values());
    }

    @Override
    public void setComment(String comment, Long appointmentId) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE_COMMENT)) {
            ps.setString(1, comment);
            ps.setLong(2, appointmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
