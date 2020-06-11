package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.exceptions.MasterNotFoundException;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.dto.MasterDTO;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.service.AppointmentService;
import net.ukr.lina_chen.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.MASTER_APPOINTMENTS_PAGE;
import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_MASTER;

public class MasterAppointmentsCommand implements Command {
    private final AppointmentService appointmentService;
    private final MasterService masterService;

    public MasterAppointmentsCommand(AppointmentService appointmentService, MasterService masterService) {
        this.appointmentService = appointmentService;
        this.masterService = masterService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        MasterDTO master;
        try {
            master = masterService.getByUserId(
                    ((UserDTO) request.getSession().getAttribute("user")).getId(), locale)
                    .orElseThrow(() -> new MasterNotFoundException("master not found"));
        } catch (MasterNotFoundException e) {
            return REDIRECT_MASTER;
        }
        List<LocalDate> dates = appointmentService.getMastersAppointmentDates(master.getId(), locale);
        Optional<String> dateString = Optional.ofNullable(request.getParameter("date"));
        List<AppointmentDTO> appointments = dateString.isPresent() ? appointmentService.getMastersDailyAppointments(
                master.getId(), LocalDate.parse(dateString.get()), locale)
                : appointmentService.getMastersAppointmentsOrderByDateTimeAsc(master.getId(), locale);
        PageRequest<AppointmentDTO> pageRequest = new PageRequest<>(appointments);
        request.setAttribute("appointments", pageRequest.makePaginatedRequest(request));
        request.setAttribute("master", master);
        request.setAttribute("dates", dates);
        return MASTER_APPOINTMENTS_PAGE;
    }
}
