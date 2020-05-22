package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.entity.ArchiveAppointment;

import java.util.List;

public class ArchiveService {
    private final DaoFactory factory = DaoFactory.getInstance();


    public List<ArchiveAppointment> getUserArchive(Long userId){
        List <ArchiveAppointment> archive;
        try(ArchiveDao archiveDao = factory.createArchiveDao()){
            archive = archiveDao.getUserArchiveAppointments(userId);
        }
        return archive;
    }

    public List<ArchiveAppointment> getMasterComments(Long masterId){
        List <ArchiveAppointment> archive;
        try(ArchiveDao archiveDao = factory.createArchiveDao()){
            archive = archiveDao.getMasterComments(masterId);
        }
        return archive;
    }

    public List<ArchiveAppointment> getAllComments(){
        List <ArchiveAppointment> archive;
        try(ArchiveDao archiveDao = factory.createArchiveDao()){
            archive = archiveDao.getAllComments();
        }
        return archive;
    }

    public void setComment(String comment, Long appointmentId){
        try(ArchiveDao archiveDao = factory.createArchiveDao()){
            archiveDao.setComment(comment, appointmentId);
        }
    }

    public ArchiveAppointment getById(Long appointmentId) {
        ArchiveAppointment appointment;
        try (ArchiveDao archiveDao = factory.createArchiveDao()) {
            appointment = archiveDao.findById(appointmentId);
        }
        return appointment;
    }
}
