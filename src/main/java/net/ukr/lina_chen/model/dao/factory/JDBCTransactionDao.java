package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.TransactionDao;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.entity.ArchiveAppointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

public class JDBCTransactionDao implements TransactionDao {
    private final Connection connection;
    private static final Logger logger = LogManager.getLogger(JDBCTransactionDao.class);
    private final Locale locale;

    public JDBCTransactionDao(Connection connection, Locale locale) {
        this.connection = connection;
        this.locale = locale;
    }

    @Override
    public Long archiveAppointment(Long appointmentId, AppointmentDao appointmentDao, ArchiveDao archiveDao) {
        long archiveId = 0L;
        try {
            connection.setAutoCommit(false);
            appointmentDao.setProvided(appointmentId);
            Appointment appointment = appointmentDao.findById(appointmentId);
            ArchiveAppointment archiveAppointment = new ArchiveAppointment(appointment, null);
            archiveId = archiveDao.create(archiveAppointment);
            appointmentDao.delete(appointmentId);
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
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
