package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.ArchiveAppointmentDTO;
import net.ukr.lina_chen.model.entity.ArchiveAppointment;

import java.util.List;
import java.util.stream.Collectors;

public class ArchiveService {
    private final DaoFactory factory = DaoFactory.getInstance();


    public List<ArchiveAppointmentDTO> getUserArchive(Long userId, boolean isLocaleEn){
        List <ArchiveAppointment> archive;
        try(ArchiveDao archiveDao = factory.createArchiveDao()){
            archive = archiveDao.getUserArchiveAppointments(userId);
        }
        return archive.stream().map(a -> getLocalizedDto(isLocaleEn, a)).collect(Collectors.toList());
    }

    public List<ArchiveAppointmentDTO> getMasterComments(Long masterId, boolean isLocaleEn){
        List <ArchiveAppointment> archive;
        try(ArchiveDao archiveDao = factory.createArchiveDao()){
            archive = archiveDao.getMasterComments(masterId);
        }
        return archive.stream().map(a -> getLocalizedDto(isLocaleEn, a)).collect(Collectors.toList());
    }

    public List<ArchiveAppointmentDTO> getAllComments(boolean isLocaleEn){
        List <ArchiveAppointment> archive;
        try(ArchiveDao archiveDao = factory.createArchiveDao()){
            archive = archiveDao.getAllComments();
        }
        return archive.stream().map(a -> getLocalizedDto(isLocaleEn, a)).collect(Collectors.toList());
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

    public ArchiveAppointmentDTO getByIdLocalized(Long appointmentId, boolean isLocaleEn) {
        return getLocalizedDto(isLocaleEn, getById(appointmentId));
    }

    private ArchiveAppointmentDTO getLocalizedDto(boolean isLocaleEn, ArchiveAppointment appointment) {
        return new ArchiveAppointmentDTO(
                new AppointmentService().getLocalizedDto(isLocaleEn, appointment), appointment.getComment());
    }
}
