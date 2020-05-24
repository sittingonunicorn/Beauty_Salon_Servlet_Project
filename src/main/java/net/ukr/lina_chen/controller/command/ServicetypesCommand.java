package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.service.BeautyservicesImpl;
import net.ukr.lina_chen.model.service.ProfessionService;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.SERVICETYPES_PAGE;

public class ServicetypesCommand implements Command {
    private final ProfessionService professionService;
    private final BeautyservicesImpl beautyservices;

    public ServicetypesCommand(ProfessionService professionService, BeautyservicesImpl beautyservices) {
        this.professionService = professionService;
        this.beautyservices = beautyservices;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if(request.getParameter("professionId")!=null){
            Long professionId = Long.parseLong(request.getParameter("professionId"));
            request.setAttribute("beautyservices", beautyservices.getBeautyservicesByProfession(professionId,
                    CommandUtility.isLocaleEn(request)));
            request.setAttribute("professionId", professionId);
        }
        request.setAttribute("servicetypes", professionService.getAllServicetypes(
                CommandUtility.isLocaleEn(request)));
        return SERVICETYPES_PAGE;
    }
}
