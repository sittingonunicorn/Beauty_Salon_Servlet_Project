package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.model.dto.ArchiveAppointmentDTO;
import net.ukr.lina_chen.model.service.ArchiveService;
import net.ukr.lina_chen.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

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
        Locale locale = CommandUtility.geLocale(request);
        request.setAttribute("masters", masterService.findAllOrderByNameAsc(locale));
        Optional<String> masterIdString = Optional.ofNullable(request.getParameter("masterId"));
        long masterId = (!masterIdString.isPresent() || Objects.equals(masterIdString.get(), "")) ? 0 :
                Long.parseLong(request.getParameter("masterId"));
        List<ArchiveAppointmentDTO> archive = (masterId == 0) ?
                archiveService.getAllCommentsOrderByDateTimeDesc(locale)
                : archiveService.getMasterCommentsOrderByDateTimeDesc(masterId, locale);
        PageRequest<ArchiveAppointmentDTO> pageRequest = new PageRequest<>(archive);
        request.setAttribute("archive", pageRequest.makePaginatedRequest(request));
        return ADMIN_COMMENTS_PAGE;
    }
}
