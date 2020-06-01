package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.mapper.ArchiveAppointmentMapper;
import net.ukr.lina_chen.model.entity.ArchiveAppointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCArchiveDao implements ArchiveDao {

    private static final Logger logger = LogManager.getLogger(JDBCArchiveDao.class);
    private final Connection connection;
    private final ArchiveAppointmentMapper archiveMapper = new ArchiveAppointmentMapper();
    private final ResourceBundle bundle;


    public JDBCArchiveDao(Connection connection, ResourceBundle bundle) {
        this.connection = connection;
        this.bundle = bundle;
    }

    public Long create(ArchiveAppointment entity) {
        Long archiveId = 0L;
        try (PreparedStatement replace = connection.prepareStatement(bundle.getString("query.replace.archive.appointment"));
             PreparedStatement getId = connection.prepareStatement(bundle.getString("query.get.archive.appointment.id"))) {
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
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.archive.appointment.by.id"))) {
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
        List<ArchiveAppointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.all.archive.appointments"));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return appointments;
    }

    @Override
    public List<ArchiveAppointment> getAllComments() {
        List<ArchiveAppointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.all.comments"));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return appointments;
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
        List<ArchiveAppointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.comments.by.master.id"))) {
            ps.setLong(1, masterId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return appointments;
    }

    @Override
    public List<ArchiveAppointment> getUserArchiveAppointments(Long userId) {
        List<ArchiveAppointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.archive.appointments.by.user.id"))) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return appointments;
    }

    @Override
    public void setComment(String comment, Long appointmentId) {
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.update.archive.appointment.set.comment"))) {
            ps.setString(1, comment);
            ps.setLong(2, appointmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
