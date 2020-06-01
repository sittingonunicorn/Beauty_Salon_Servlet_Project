package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.exceptions.TimeIsBusyException;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.service.AppointmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_TIME;
import static net.ukr.lina_chen.controller.utility.PagesContainer.SAVE_PAGE;

public class SaveCommand implements Command {
    AppointmentService appointmentService;
    private static final Logger logger = LogManager.getLogger(SaveCommand.class);

    public SaveCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        Appointment appointment = (Appointment) request.getSession().getAttribute("appointment");
        LocalDate date = LocalDate.parse(request.getParameter("appointmentDate"));
        LocalTime time = LocalTime.parse(request.getParameter("appointmentTime"));
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setProvided(false);
        Optional<Long> appointmentId = Optional.empty();
        try {
            appointmentId = Optional.of(appointmentService.saveAppointment(appointment, locale));
        } catch (TimeIsBusyException e) {
            logger.error(e.getMessage());
            return REDIRECT_TIME + "/" + appointment.getMaster().getId() + "?timeBusy=true";
        }
        request.setAttribute(
                "appointment", appointmentId.map(
                        id -> appointmentService.getById(id, locale)).orElse(null));
        return SAVE_PAGE;
    }
}
