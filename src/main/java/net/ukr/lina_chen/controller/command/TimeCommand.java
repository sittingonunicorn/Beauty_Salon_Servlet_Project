package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.service.AppointmentService;
import net.ukr.lina_chen.model.service.MasterService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.ukr.lina_chen.controller.utility.IConstants.*;
import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class TimeCommand implements Command {
    private MasterService masterService;
    private AppointmentService appointmentService;
    private static final Logger logger = LogManager.getLogger(TimeCommand.class);

    public TimeCommand(MasterService masterService, AppointmentService appointmentService) {
        this.masterService = masterService;
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        String masterId = request.getParameter("masterId");
        Optional<Master> master = masterService.getById(Long.parseLong(masterId), locale);
        Appointment appointment = (Appointment) request.getSession().getAttribute("appointment");
        if (!master.isPresent()) {
            return REDIRECT_SERVICETYPES;
        }
        appointment.setMaster(master.get());
        request.setAttribute("appointment", appointment);

        Map<LocalDate, List<LocalTime>> dateTime = getDateTime(master.get(), locale);
        request.setAttribute("workingHours",
                Stream.iterate(master.get().getTimeBegin(), curr -> curr.plusHours(1)).
                        limit(ChronoUnit.HOURS.between(master.get().getTimeBegin(), master.get().getTimeEnd())).
                        collect(Collectors.toList()));
        request.setAttribute("dateTime", dateTime);
        request.setAttribute("master", masterService.getByUserId(master.get().getUser().getId(), locale)
                .orElse(null));
        return TIME_PAGE;
    }

    private Map<LocalDate, List<LocalTime>> getDateTime(Master master, Locale locale) {
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
        List<AppointmentDTO> appointments = appointmentService.getMastersAppointmentsOrderByDateTimeAsc(
                masterId, locale);
        return appointments.stream()
                .map(app -> LocalDateTime.of(LocalDate.parse(app.getDate(),
                        DateTimeFormatter.ofPattern(ResourceBundle.getBundle("messages", locale)
                                .getString(DATE_FORMAT))),
                        app.getTime()))
                .collect(Collectors.toList());
    }

}

