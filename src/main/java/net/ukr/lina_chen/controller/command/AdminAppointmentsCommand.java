package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static net.ukr.lina_chen.controller.utility.PagesContainer.ADMIN_APPOINTMENTS_PAGE;

public class AdminAppointmentsCommand implements Command {
    private final AppointmentService appointmentService;

    public AdminAppointmentsCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        PageRequest<AppointmentDTO> pageRequest = new PageRequest<>(appointmentService.getAllOrderByDateTimeAsc(locale));
        request.setAttribute("appointments", pageRequest.makePaginatedRequest(request));
        return ADMIN_APPOINTMENTS_PAGE;
    }
}
