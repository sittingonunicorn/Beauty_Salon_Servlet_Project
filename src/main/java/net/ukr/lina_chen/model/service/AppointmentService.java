package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.exceptions.TimeIsBusyException;
import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.entity.Appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static net.ukr.lina_chen.controller.utility.IConstants.DATE_FORMAT;

public class AppointmentService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<AppointmentDTO> getMastersAppointmentsOrderByDateTimeAsc(Long masterId, Locale locale) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(
                ResourceBundle.getBundle("queries", locale))) {
            appointments = appointmentDao.getMasterAppointments(masterId);
        }
        return appointments.stream().map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    public AppointmentDTO getById(Long appointmentId, Locale locale) {
        Appointment appointment;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(
                ResourceBundle.getBundle("queries", locale))) {
            appointment = appointmentDao.findById(appointmentId);
        }
        return getLocalizedDto(appointment, locale);
    }

    public List<AppointmentDTO> getAllOrderByDateTimeAsc(Locale locale) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(
                ResourceBundle.getBundle("queries", locale))) {
            appointments = appointmentDao.findAll();
        }
        return appointments.stream().map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    public Long saveAppointment(Appointment appointment, Locale locale) throws TimeIsBusyException {
        Long id;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(
                ResourceBundle.getBundle("queries", locale))) {
            id = appointmentDao.create(appointment);
        }
        return id;
    }

    public AppointmentDTO getLocalizedDto(Appointment appointment, Locale locale) {
        return AppointmentDTO.AppointmentDTOBuilder.appointmentDTO()
                .withBeautyService(appointment.getBeautyService().getName())
                .withMasterName(appointment.getMaster().getUser().getName())
                .withUserName(appointment.getUser().getName())
                .withId(appointment.getId())
                .withDate(getLocalizedDate(appointment.getDate(), locale))
                .withTime(appointment.getTime())
                .withProvided(appointment.isProvided())
                .build();
    }

    private String getLocalizedDate (LocalDate date, Locale locale){
        return date.format(DateTimeFormatter.ofPattern(
                ResourceBundle.getBundle("messages", locale).getString(DATE_FORMAT)));
    }
}
