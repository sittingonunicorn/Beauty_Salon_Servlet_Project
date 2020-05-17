package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dao.factory.JDBCAppointmentDao;
import net.ukr.lina_chen.model.entity.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import static net.ukr.lina_chen.model.dao.AppointmentDao.QUERY_DELETE;
import static net.ukr.lina_chen.model.dao.ArchiveDao.QUERY_REPLACE;

public class TransactionDao {
    private final Connection connection;
    private final DaoFactory factory = DaoFactory.getInstance();
    private final AppointmentDao appointmentDao = factory.createAppointmentDao();
    private final ArchiveDao archiveDao = factory.createArchiveDao();
    private static final Logger logger = LogManager.getLogger(JDBCAppointmentDao.class);

    public TransactionDao(Connection connection) {
        this.connection = connection;
    }

    public void archiveAppointment(Appointment appointment) {
        try (PreparedStatement create = connection.prepareStatement(QUERY_REPLACE);
             PreparedStatement delete = connection.prepareStatement(QUERY_DELETE)) {
            delete.setLong(1, appointment.getId());
            create.setLong(1, appointment.getMaster().getId());
            create.setLong(2, appointment.getUser().getId());
            create.setLong(3, appointment.getBeautyService().getId());
            create.setTime(4, Time.valueOf(appointment.getTime()));
            create.setDate(5, Date.valueOf(appointment.getDate()));
            create.setBoolean(6, true);
            create.setString(7, null);
            connection.setAutoCommit(false);
            create.executeUpdate();
            delete.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}
