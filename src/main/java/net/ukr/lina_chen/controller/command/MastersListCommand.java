package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.Appointment;
import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.entity.Profession;
import net.ukr.lina_chen.model.entity.User;
import net.ukr.lina_chen.model.service.BeautyservicesImpl;
import net.ukr.lina_chen.model.service.ProfessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static net.ukr.lina_chen.controller.utility.PagesContainer.MASTERLIST_PAGE;

public class MastersListCommand implements Command {
    private final ProfessionService professionService;
    private final BeautyservicesImpl beautyservices;
    private static final Logger logger = LogManager.getLogger(MastersListCommand.class);

    public MastersListCommand(ProfessionService professionService, BeautyservicesImpl beautyservices) {
        this.professionService = professionService;
        this.beautyservices = beautyservices;
    }


    @Override
    public String execute(HttpServletRequest request) {
        Appointment appointment = new Appointment();
        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        //TODO refactor
        String[] params = path.replaceAll(".*/app/user/masters/", "").split("/");
        Long professionId = Long.parseLong(params[0]);
        Long beautyserviceId = Long.parseLong(params[1]);
        appointment.setBeautyService(beautyservices.getById(beautyserviceId).get());
        appointment.setUser((User)session.getAttribute("user"));
        Profession profession = professionService.getById(professionId);
        session.setAttribute("appointment", appointment);
        request.setAttribute("masters", new ArrayList<Master>(profession.getMasters()));
        return MASTERLIST_PAGE;
    }
}
