package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.service.AppointmentService;
import net.ukr.lina_chen.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.USER_APPOINTMENTS_PAGE;

public class UserAppointmentsCommand implements Command {
    private final AppointmentService appointmentService;
    private final UserService userService;

    public UserAppointmentsCommand(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("0"));

        List<AppointmentDTO> appointments = appointmentService.getUsersAppointments(
                ((UserDTO) request.getSession().getAttribute("user")).getId(), locale);
        PageRequest<AppointmentDTO> pageRequest = new PageRequest<>(appointments);
        appointments = pageRequest.getPage(page);
        request.setAttribute("appointments", appointments);
        request.setAttribute("pageNumbers", pageRequest.getPageNumbers());
        return USER_APPOINTMENTS_PAGE;
    }
}
