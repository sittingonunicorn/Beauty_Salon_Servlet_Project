package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.ArchiveAppointment;
import net.ukr.lina_chen.model.service.ArchiveService;
import net.ukr.lina_chen.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static net.ukr.lina_chen.controller.utility.PagesContainer.ADMIN_COMMENTS_PAGE;

public class AdminCommentsCommand implements Command {
    private final ArchiveService archiveService;
    private final MasterService masterService;

    public AdminCommentsCommand(ArchiveService archiveService, MasterService masterService) {
        this.archiveService = archiveService;
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("masters", masterService.findAll());
        long masterId = (request.getParameter("masterId") != null) ?
                Long.parseLong(request.getParameter("masterId")) : 0;
        request.setAttribute("archive", (masterId != 0) ?
                archiveService.getMasterComments(masterId, CommandUtility.isLocaleEn(request))
                : archiveService.getAllComments(CommandUtility.isLocaleEn(request)));
        return ADMIN_COMMENTS_PAGE;
    }
}
