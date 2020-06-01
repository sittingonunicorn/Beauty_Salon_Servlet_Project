package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.exceptions.InvalidDataException;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.service.BeautyservicesImpl;
import net.ukr.lina_chen.model.service.MasterService;
import net.ukr.lina_chen.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class MastersListCommand implements Command {
    private final MasterService masterService;
    private final BeautyservicesImpl beautyservices;
    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(MastersListCommand.class);

    public MastersListCommand(MasterService masterService, BeautyservicesImpl beautyservices, UserService userService) {
        this.masterService = masterService;
        this.beautyservices = beautyservices;
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        Appointment appointment = new Appointment();
        HttpSession session = request.getSession();
        Long professionId = Long.parseLong(request.getParameter("professionId"));
        Long beautyserviceId = Long.parseLong(request.getParameter("beautyserviceId"));
        try {
            appointment.setBeautyService(beautyservices.getById(beautyserviceId, locale)
                    .orElseThrow(InvalidDataException::new));
        } catch (InvalidDataException e) {
            logger.error(e.getMessage());
            return REDIRECT_SERVICETYPES;
        }
        try {
            appointment.setUser(userService.getUserById(((UserDTO)session.getAttribute("user")).getId(), locale)
                    .orElseThrow(InvalidDataException::new));
        } catch (InvalidDataException e) {
            logger.error(e.getMessage());
            return REDIRECT_LOGIN;
        }
        session.setAttribute("appointment", appointment);
        request.setAttribute("masters", masterService.findByProfessionIdOrderByNameAsc(professionId, locale));
        return MASTER_LIST_PAGE;
    }
}
