package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.exceptions.TimeIsBusyException;
import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.entity.Master;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.ukr.lina_chen.controller.utility.IConstants.*;

public class AppointmentService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<AppointmentDTO> getMastersAppointmentsOrderByDateTimeAsc(Long masterId, Locale locale) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(locale)) {
            appointments = appointmentDao.getMasterAppointments(masterId);
        }
        return appointments.stream().map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    public AppointmentDTO getById(Long appointmentId, Locale locale) {
        Appointment appointment;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(locale)) {
            appointment = appointmentDao.findById(appointmentId);
        }
        return getLocalizedDto(appointment, locale);
    }

    public List<AppointmentDTO> getAllOrderByDateTimeAsc(Locale locale) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(locale)) {
            appointments = appointmentDao.findAll();
        }
        return appointments.stream().map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    public Long saveAppointment(Appointment appointment, Locale locale) throws TimeIsBusyException {
        Long id;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(locale)) {
            id = appointmentDao.create(appointment);
        }
        return id;
    }

    public List<LocalDate> getMastersAppointmentDates(Long masterId, Locale locale){
        List<Date> dates;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(locale)) {
            dates = appointmentDao.findMastersAppointmentDates(masterId);
        }
        return dates.stream().map(Date::toLocalDate).collect(Collectors.toList());
    }

    public List<AppointmentDTO> getMastersDailyAppointments(Long masterId, LocalDate date, Locale locale){
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(locale)) {
            appointments = appointmentDao.findMastersDailyAppointments(masterId, Date.valueOf(date));
        }
        return appointments.stream().map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getUsersAppointments(Long userId, Locale locale){
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao(locale)) {
            appointments = appointmentDao.findUsersAppointments(userId);
        }
        return appointments.stream().map(a -> getLocalizedDto(a, locale))
                .collect(Collectors.toList());
    }

    AppointmentDTO getLocalizedDto(Appointment appointment, Locale locale) {
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
    public Map<LocalDate, List<LocalTime>> getDateTime(Master master, Locale locale) {
        Long masterId = master.getId();
        List<LocalDate> dates = getDates(master);
        List<LocalDateTime> busyTime = getMastersBusySchedule(masterId, locale);
        return getScheduleMap(master, dates, busyTime);
    }

    private Map<LocalDate, List<LocalTime>> getScheduleMap(Master master, List<LocalDate> dates, List<LocalDateTime> busyTime) {
        Map<LocalDate, List<LocalTime>> dateTime = new LinkedHashMap<>();
        LocalDateTime now = LocalDateTime.now();
        for (LocalDate date : dates) {
            List<LocalTime> timeList = Stream.iterate(master.getTimeBegin(), time -> time.plusHours(ITERATE_UNIT))
                    .limit(ChronoUnit.HOURS.between(master.getTimeBegin(), master.getTimeEnd()))
                    .filter(time -> !busyTime.contains(LocalDateTime.of(date, time)))
                    .filter(time -> now.isBefore(LocalDateTime.of(date, time)))
                    .collect(Collectors.toList());
            dateTime.put(date, timeList);
        }
        return dateTime;
    }

    private List<LocalDate> getDates(Master master) {
        LocalDate startDate = LocalDateTime.now().isBefore(
                LocalDateTime.of(LocalDate.now(), master.getTimeEnd())) ?
                LocalDate.now() : LocalDate.now().plusDays(ITERATE_UNIT);
        return Stream.iterate(startDate, date -> date.plusDays(ITERATE_UNIT))
                .limit(ChronoUnit.DAYS.between(startDate, startDate.plusDays(SCHEDULE_DAYS)))
                .collect(Collectors.toList());
    }

    private List<LocalDateTime> getMastersBusySchedule(Long masterId, Locale locale) {
        List<AppointmentDTO> appointments = getMastersAppointmentsOrderByDateTimeAsc(
                masterId, locale);
        return appointments.stream()
                .map(app -> LocalDateTime.of(LocalDate.parse(app.getDate(),
                        DateTimeFormatter.ofPattern(ResourceBundle.getBundle("messages", locale)
                                .getString(DATE_FORMAT))),
                        app.getTime()))
                .collect(Collectors.toList());
    }
}
