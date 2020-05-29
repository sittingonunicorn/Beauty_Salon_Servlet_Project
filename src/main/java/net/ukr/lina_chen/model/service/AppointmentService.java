package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.exceptions.TimeIsBusyException;
import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static net.ukr.lina_chen.controller.utility.IConstants.DATE_FORMAT;

public class AppointmentService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<AppointmentDTO> getMastersAppointmentsOrderByDateTimeAsc(Long masterId, boolean isLocaleEn) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointments = appointmentDao.getMasterAppointments(masterId);
        }
        return appointments.stream().map(a -> getLocalizedDto(isLocaleEn, a))
                .sorted(Comparator.comparing(AppointmentDTO::getDate)
                        .thenComparing(AppointmentDTO::getTime))
                .collect(Collectors.toList());
    }

    public AppointmentDTO getById(Long appointmentId, boolean isLocaleEn) {
        Appointment appointment;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointment = appointmentDao.findById(appointmentId);
        }
        return getLocalizedDto(isLocaleEn, appointment);
    }

    public List<AppointmentDTO> getAllOrderByDateTimeAsc(boolean isLocaleEn) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointments = appointmentDao.findAll();
        }
        return appointments.stream().map(a -> getLocalizedDto(isLocaleEn, a))
                .sorted(Comparator.comparing(AppointmentDTO::getDate)
                        .thenComparing(AppointmentDTO::getTime))
                .collect(Collectors.toList());
    }

    public Long saveAppointment(Appointment appointment) throws TimeIsBusyException {
        Long id;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            id = appointmentDao.create(appointment);
        }
        return id;
    }

    public AppointmentDTO getLocalizedDto(boolean isLocaleEn, Appointment appointment) {
        Optional<Master> masterOptional = new MasterService().getById(appointment.getMaster().getId());
        User master = masterOptional.isPresent()? masterOptional.get().getUser(): new User();
        return AppointmentDTO.AppointmentDTOBuilder.appointmentDTO()
                .withBeautyService(isLocaleEn ? appointment.getBeautyService().getName()
                        : appointment.getBeautyService().getNameUkr())
                .withMasterName(isLocaleEn ? master.getName(): master.getNameUkr())
                .withUserName(isLocaleEn ? appointment.getUser().getName()
                        : appointment.getUser().getNameUkr())
                .withId(appointment.getId())
                .withDate(getLocalizedDate(appointment.getDate(), isLocaleEn))
                .withTime(appointment.getTime())
                .withProvided(appointment.isProvided())
                .build();
    }

    private String getLocalizedDate (LocalDate date, boolean isLocaleEn){
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                isLocaleEn? Locale.US: new Locale("ua"));
        return date.format(DateTimeFormatter.ofPattern(bundle.getString(DATE_FORMAT)));
    }
}
