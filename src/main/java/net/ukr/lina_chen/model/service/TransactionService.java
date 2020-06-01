package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.TransactionDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class TransactionService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public Long archiveAppointment(Long appointmentId, Locale locale) throws Exception {
        Long archiveAppointmentId;
        try(AppointmentDao appointmentDao = factory.createAppointmentDao(ResourceBundle.getBundle("queries", locale));
            ArchiveDao archiveDao = factory.createArchiveDao(ResourceBundle.getBundle("queries", locale));
            TransactionDao transactionDao = factory.createTransactionDao(ResourceBundle.getBundle("queries", locale))){
            archiveAppointmentId = transactionDao.archiveAppointment(appointmentId, appointmentDao, archiveDao);
        }
        return archiveAppointmentId;
    }
}
