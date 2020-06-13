package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;

import static net.ukr.lina_chen.controller.utility.PagesContainer.CREATED_PAGE;

public class CreatedCommand implements Command {
    private final AppointmentService appointmentService;

    public CreatedCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        Long appointmentId = ((Appointment)request.getSession().getAttribute("appointment")).getId();
        request.setAttribute(
                "appointment", appointmentService.getById(appointmentId, locale));
        return CREATED_PAGE;
    }
}
