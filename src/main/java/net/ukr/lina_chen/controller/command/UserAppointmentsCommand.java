package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

import static net.ukr.lina_chen.controller.utility.PagesContainer.USER_APPOINTMENTS_PAGE;

public class UserAppointmentsCommand implements Command {
    private final AppointmentService appointmentService;

    public UserAppointmentsCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        List<AppointmentDTO> appointments = appointmentService.getUsersAppointments(
                ((UserDTO) request.getSession().getAttribute("user")).getId(), locale);
        PageRequest<AppointmentDTO> pageRequest = new PageRequest<>(appointments);
        request.setAttribute("appointments", pageRequest.makePaginatedRequest(request));
        return USER_APPOINTMENTS_PAGE;
    }
}
