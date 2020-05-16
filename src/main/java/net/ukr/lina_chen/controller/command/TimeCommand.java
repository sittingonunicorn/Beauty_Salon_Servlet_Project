package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.dao.factory.JDBCProfessionDao;
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
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.ukr.lina_chen.controller.utility.PagesContainer.TIME_PAGE;

public class TimeCommand implements Command {
    MasterService masterService;
    AppointmentService appointmentService;
    private static final Logger logger = LogManager.getLogger(TimeCommand.class);

    public TimeCommand(MasterService masterService, AppointmentService appointmentService) {
        this.masterService = masterService;
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path = request.getRequestURI();
        Long masterId = Long.parseLong(path.replaceAll(".*/app/user/time/", ""));
        Optional<Master> master = masterService.getById(masterId);
        Appointment appointment = (Appointment) request.getSession().getAttribute("appointment");
        master.ifPresent(appointment::setMaster);
        request.setAttribute("appointment", appointment);
        List<Appointment> appointments = appointmentService.getMastersAppointments(masterId);
        List<LocalDateTime> busyTime = appointments.stream()
                .map(app -> LocalDateTime.of(app.getDate(), app.getTime()))
                .collect(Collectors.toList());
        Map<LocalDate, List<LocalTime>> dateTime = new HashMap<>();
        List<LocalDate> dates = Stream.iterate(LocalDate.now(), date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(7)))
                .collect(Collectors.toList());
        LocalDateTime ldt = LocalDateTime.now();
        for (LocalDate date : dates) {
            List<LocalTime> timeList = Stream.iterate(master.get().getTimeBegin(), time -> time.plusHours(1))
                    .limit(ChronoUnit.HOURS.between(master.get().getTimeBegin(), master.get().getTimeEnd()))
                    .filter(time -> !busyTime.contains(LocalDateTime.of(date, time)))
                    .filter(time ->ldt.isBefore(LocalDateTime.of(date, time)))
                    .collect(Collectors.toList());
            dateTime.put(date, timeList);
        }
        request.setAttribute("time",
                Stream.iterate(master.get().getTimeBegin(), curr -> curr.plusHours(1)).
                        limit(ChronoUnit.HOURS.between(master.get().getTimeBegin(), master.get().getTimeEnd())).
                        collect(Collectors.toList()));
        request.setAttribute("dateTime", dateTime);
        return TIME_PAGE;
    }
}
