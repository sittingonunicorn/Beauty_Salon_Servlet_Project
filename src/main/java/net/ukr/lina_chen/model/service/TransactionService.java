package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.TransactionDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;

import java.util.Locale;

public class TransactionService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public Long archiveAppointment(Long appointmentId, Locale locale) throws Exception {
        Long archiveAppointmentId;
        try(AppointmentDao appointmentDao = factory.createAppointmentDao(locale);
            ArchiveDao archiveDao = factory.createArchiveDao(locale);
            TransactionDao transactionDao = factory.createTransactionDao(locale)){
            archiveAppointmentId = transactionDao.archiveAppointment(appointmentId, appointmentDao, archiveDao);
        }
        return archiveAppointmentId;
    }
}
