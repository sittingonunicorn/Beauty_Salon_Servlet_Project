package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.service.ArchiveService;
import net.ukr.lina_chen.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;

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
        request.setAttribute("masters", masterService.findAllOrderByNameAsc(CommandUtility.isLocaleEn(request)));
        long masterId = (request.getParameter("masterId") != null) ?
                Long.parseLong(request.getParameter("masterId")) : 0;
        request.setAttribute("archive", (masterId != 0) ?
                archiveService.getMasterCommentsOrderByDateTimeDesc(masterId, CommandUtility.isLocaleEn(request))
                : archiveService.getAllCommentsOrderByDateTimeDesc(CommandUtility.isLocaleEn(request)));
        return ADMIN_COMMENTS_PAGE;
    }
}
