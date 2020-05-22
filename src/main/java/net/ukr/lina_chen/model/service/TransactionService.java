package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.TransactionDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;

public class TransactionService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public Long archiveAppointment(Long appointmentId) throws Exception {
        Long archiveAppointmentId;
        try(AppointmentDao appointmentDao = factory.createAppointmentDao();
            ArchiveDao archiveDao = factory.createArchiveDao();
            TransactionDao transactionDao = factory.createTransactionDao()){
            archiveAppointmentId = transactionDao.archiveAppointment(appointmentId, appointmentDao, archiveDao);
        }
        return archiveAppointmentId;
    }
}
