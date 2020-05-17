package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.User;
import net.ukr.lina_chen.model.service.ArchiveService;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.ARCHIVE_APPOINTMENTS_PAGE;

public class ArchiveCommand implements Command{
    private final ArchiveService archiveService;

    public ArchiveCommand(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("archiveForUser",
                archiveService.getUserArchive(((User)request.getSession().getAttribute("user")).getId()));

        return ARCHIVE_APPOINTMENTS_PAGE;
    }
}
