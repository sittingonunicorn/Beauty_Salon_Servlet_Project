package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.exceptions.MasterNotFoundException;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.service.AppointmentService;
import net.ukr.lina_chen.model.service.MasterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class TimeCommand implements Command {
    private MasterService masterService;
    private AppointmentService appointmentService;
    Logger logger = LogManager.getLogger(TimeCommand.class);

    public TimeCommand(MasterService masterService, AppointmentService appointmentService) {
        this.masterService = masterService;
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        Optional<String> masterIdString = Optional.ofNullable(request.getParameter("masterId"));
        if(!masterIdString.isPresent()){
            return REDIRECT_SERVICETYPES + ERROR_PARAM;
        }
        Long masterId= Long.parseLong(masterIdString.get());
        Master master;
        try {
            master = masterService.getById(masterId, locale)
                    .orElseThrow(()->new MasterNotFoundException(masterId));
        } catch (MasterNotFoundException e) {
            logger.warn(e.getLocalizedMessage());
            return REDIRECT_SERVICETYPES + ERROR_PARAM;
        }
        Appointment appointment = (Appointment) request.getSession().getAttribute("appointment");
        appointment.setMaster(master);
        Map<LocalDate, List<LocalTime>> dateTime = appointmentService.getDateTime(master, locale);
        request.setAttribute("workingHours", masterService.getMastersWorkingHours(master));
        request.setAttribute("dateTime", dateTime);
        request.setAttribute("master", masterService.getByUserId(master.getUser().getId(), locale)
                .orElse(null));
        return TIME_PAGE;
    }
}

