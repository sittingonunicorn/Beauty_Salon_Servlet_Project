package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.service.BeautyservicesImpl;
import net.ukr.lina_chen.model.service.ServiceTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static net.ukr.lina_chen.controller.utility.PagesContainer.SERVICETYPES_PAGE;

public class ServicetypesCommand implements Command {
    private final ServiceTypeService serviceTypeService;
    private final BeautyservicesImpl beautyservices;

    public ServicetypesCommand(ServiceTypeService serviceTypeService, BeautyservicesImpl beautyservices) {
        this.serviceTypeService = serviceTypeService;
        this.beautyservices = beautyservices;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        String professionIdString = request.getParameter("professionId");
        if(professionIdString!=null){
            Long professionId = Long.parseLong(professionIdString);
            request.setAttribute("beautyservices", beautyservices.getBeautyservicesByProfession(professionId, locale));
            request.setAttribute("professionId", professionId);
        }
        request.setAttribute("servicetypes", serviceTypeService.getAllServicetypes(locale));
        return SERVICETYPES_PAGE;
    }
}
