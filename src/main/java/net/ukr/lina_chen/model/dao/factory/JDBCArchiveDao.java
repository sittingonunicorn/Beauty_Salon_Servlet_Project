package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.mapper.ArchiveAppointmentMapper;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.entity.ArchiveAppointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void create(ArchiveAppointment entity) throws SQLException {

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
        return null;
    }

    @Override
    public void update(ArchiveAppointment entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<ArchiveAppointment> getMasterArchiveAppointments(Long masterId) {
        return null;
    }

    @Override
    public List<ArchiveAppointment> getUserArchiveAppointments(Long userId) {
        Map<Long, ArchiveAppointment> archiveAppointments = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_BY_USER_ID)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ArchiveAppointment appointment = archiveMapper.extractFromResultSet(rs);
                    appointment = archiveMapper.makeUnique(archiveAppointments, appointment);
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
