package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.entity.Appointment;


import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<AppointmentDTO> getMastersAppointments(Long masterId, boolean isLocaleEn) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointments = appointmentDao.getMasterAppointments(masterId);
        }
        return appointments.stream().map(a -> getLocalizedDto(isLocaleEn, a)).collect(Collectors.toList());
    }

    public AppointmentDTO getById(Long appointmentId, boolean isLocaleEn) {
        Appointment appointment;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointment = appointmentDao.findById(appointmentId);
        }
        return getLocalizedDto(isLocaleEn, appointment);
    }

    public List<AppointmentDTO> getAll(boolean isLocaleEn) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointments = appointmentDao.findAll();
        }
        return appointments.stream().map(a -> getLocalizedDto(isLocaleEn, a)).collect(Collectors.toList());
    }

    public Long saveAppointment(Appointment appointment) throws SQLException {
        Long id;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            id = appointmentDao.create(appointment);
        }
        return id;
    }

    public AppointmentDTO getLocalizedDto(boolean isLocaleEn, Appointment appointment) {
        return AppointmentDTO.AppointmentDTOBuilder.appointmentDTO()
                .withBeautyService(isLocaleEn ? appointment.getBeautyService().getName()
                        : appointment.getBeautyService().getNameUkr())
                .withMasterName(isLocaleEn ? appointment.getMaster().getUser().getName()
                        : appointment.getMaster().getUser().getNameUkr())
                .withUserName(isLocaleEn ? appointment.getUser().getName()
                        : appointment.getUser().getNameUkr())
                .withId(appointment.getId())
                .withDate(appointment.getDate())
                .withTime(appointment.getTime())
                .withProvided(appointment.isProvided())
                .build();
    }
}
