package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.service.BeautyservicesImpl;
import net.ukr.lina_chen.model.service.MasterService;
import net.ukr.lina_chen.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static net.ukr.lina_chen.controller.utility.PagesContainer.MASTER_LIST_PAGE;

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
        Appointment appointment = new Appointment();
        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        //TODO refactor
        //request.getParameter()
        String[] params = path.replaceAll(".*/app/user/masters/", "").split("/");
        Long professionId = Long.parseLong(params[0]);
        Long beautyserviceId = Long.parseLong(params[1]);
        appointment.setBeautyService(beautyservices.getById(beautyserviceId).get());
        appointment.setUser(userService.getUserById(((UserDTO)session.getAttribute("user")).getId()).get());
        session.setAttribute("appointment", appointment);
        request.setAttribute("masters", masterService.findByProfessionIdOrderByNameAsc(professionId,
                CommandUtility.isLocaleEn(request)));
        return MASTER_LIST_PAGE;
    }
}
