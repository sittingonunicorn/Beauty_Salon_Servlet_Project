package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.model.dto.AppointmentDTO;
import net.ukr.lina_chen.model.dto.MasterDTO;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.service.AppointmentService;
import net.ukr.lina_chen.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.MASTER_APPOINTMENTS_PAGE;

public class MasterAppointmentsCommand implements Command {
    private final AppointmentService appointmentService;
    private final MasterService masterService;

    public MasterAppointmentsCommand(AppointmentService appointmentService, MasterService masterService) {
        this.appointmentService = appointmentService;
        this.masterService = masterService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("0"));
       Optional<MasterDTO> master = masterService.getByUserId(
               ((UserDTO) request.getSession().getAttribute("user")).getId(), CommandUtility.isLocaleEn(request));
        List<AppointmentDTO> appointments =appointmentService.getMastersAppointmentsOrderByDateTimeAsc(
                master.get().getId(), CommandUtility.isLocaleEn(request));
        PageRequest<AppointmentDTO> pageRequest = new PageRequest<>(appointments);
        appointments = pageRequest.getPage(page);
        request.setAttribute("appointments", appointments);
        request.setAttribute("master", master.get());
        request.setAttribute("pageNumbers", pageRequest.getPageNumbers());
        return MASTER_APPOINTMENTS_PAGE;
    }
}
