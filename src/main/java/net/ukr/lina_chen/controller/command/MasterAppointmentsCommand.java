package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.entity.User;
import net.ukr.lina_chen.model.service.AppointmentService;
import net.ukr.lina_chen.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;

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
       Optional< Master> master = masterService.getByUserId(
               ((User) request.getSession().getAttribute("user")).getId());
        request.setAttribute("appointments",
                appointmentService.getMastersAppointments(master.get().getId()));
        System.out.println(appointmentService.getMastersAppointments(master.get().getId()));
        request.setAttribute("master", master.get());
        return MASTER_APPOINTMENTS_PAGE;
    }
}
