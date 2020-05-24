package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.service.ProfessionService;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.SERVICETYPES_PAGE;

public class ServicetypesCommand implements Command {
    private final ProfessionService professionService;

    public ServicetypesCommand(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("servicetypes", professionService.getAllServicetypes(
                CommandUtility.isLocaleEn(request)));
        return SERVICETYPES_PAGE;
    }
}
