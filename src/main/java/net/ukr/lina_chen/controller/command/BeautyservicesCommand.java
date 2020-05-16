package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.service.BeautyservicesImpl;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.BEAUTYSERVICES_PAGE;

public class BeautyservicesCommand implements Command {
    private final BeautyservicesImpl beautyservices;

    public BeautyservicesCommand(BeautyservicesImpl beautyservices) {
        this.beautyservices = beautyservices;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path = request.getRequestURI();
        Long professionId = Long.parseLong(path.replaceAll(".*/app/user/beautyservices/", ""));
        request.setAttribute("beautyservices", beautyservices.getBeautyservicesByProfession(professionId));
        request.setAttribute("professionId", professionId);
        return BEAUTYSERVICES_PAGE;
    }
}
