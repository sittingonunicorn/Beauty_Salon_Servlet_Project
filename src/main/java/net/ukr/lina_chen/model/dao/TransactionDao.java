package net.ukr.lina_chen.model.dao;

public interface TransactionDao extends AutoCloseable{
    Long archiveAppointment(Long appointmentId, AppointmentDao appointmentDao, ArchiveDao archiveDao);

}
