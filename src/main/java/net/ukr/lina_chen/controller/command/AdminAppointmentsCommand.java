package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.ADMIN_APPOINTMENTS_PAGE;

public class AdminAppointmentsCommand implements Command {
    private final AppointmentService appointmentService;

    public AdminAppointmentsCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("0"));
        List<Appointment> appointments = appointmentService.getAll();
        PageRequest<Appointment> pageRequest = new PageRequest<>(appointments);
        appointments = pageRequest.getPage(page);
        request.setAttribute("appointments", appointments);
        request.setAttribute("pageNumbers", pageRequest.getPageNumbers());
        return ADMIN_APPOINTMENTS_PAGE;
    }
}