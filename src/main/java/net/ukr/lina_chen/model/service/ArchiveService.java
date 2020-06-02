package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.ArchiveDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.ArchiveAppointmentDTO;
import net.ukr.lina_chen.model.entity.ArchiveAppointment;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ArchiveService {
    private final DaoFactory factory = DaoFactory.getInstance();


    public List<ArchiveAppointmentDTO> getUserArchive(Long userId, Locale locale) {
        List<ArchiveAppointment> archive;
        try (ArchiveDao archiveDao = factory.createArchiveDao(locale)) {
            archive = archiveDao.getUserArchiveAppointments(userId);
        }
        return archive.stream()
                .map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    public List<ArchiveAppointmentDTO> getMasterCommentsOrderByDateTimeDesc(Long masterId, Locale locale) {
        List<ArchiveAppointment> archive;
        try (ArchiveDao archiveDao = factory.createArchiveDao(locale)) {
            archive = archiveDao.getMasterComments(masterId);
        }
        return archive.stream()
                .map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    public List<ArchiveAppointmentDTO> getAllCommentsOrderByDateTimeDesc(Locale locale) {
        List<ArchiveAppointment> archive;
        try (ArchiveDao archiveDao = factory.createArchiveDao(locale)) {
            archive = archiveDao.getAllComments();
        }
        return archive.stream()
                .map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    public void setComment(String comment, Long appointmentId, Locale locale) {
        try (ArchiveDao archiveDao = factory.createArchiveDao(locale)) {
            archiveDao.setComment(comment, appointmentId);
        }
    }

    public ArchiveAppointment getById(Long appointmentId, Locale locale) {
        ArchiveAppointment appointment;
        try (ArchiveDao archiveDao = factory.createArchiveDao(locale)) {
            appointment = archiveDao.findById(appointmentId);
        }
        return appointment;
    }

    public ArchiveAppointmentDTO getByIdLocalized(Long appointmentId, Locale locale) {
        return getLocalizedDto(getById(appointmentId, locale), locale);
    }

    private ArchiveAppointmentDTO getLocalizedDto(ArchiveAppointment appointment, Locale locale) {
        return new ArchiveAppointmentDTO(
                new AppointmentService().getLocalizedDto(appointment, locale), appointment.getComment());
    }
}
