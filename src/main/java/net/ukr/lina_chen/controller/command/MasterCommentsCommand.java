package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.exceptions.MasterNotFoundException;
import net.ukr.lina_chen.model.dto.ArchiveAppointmentDTO;
import net.ukr.lina_chen.model.dto.MasterDTO;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.service.ArchiveService;
import net.ukr.lina_chen.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

import static net.ukr.lina_chen.controller.utility.PagesContainer.MASTER_COMMENTS_PAGE;
import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_MASTER;

public class MasterCommentsCommand implements Command{
    private final ArchiveService archiveService;
    private final MasterService masterService;

    public MasterCommentsCommand(ArchiveService archiveService, MasterService masterService) {
        this.archiveService = archiveService;
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        MasterDTO master;
        try {
            master = masterService.getByUserId(
                    ((UserDTO) request.getSession().getAttribute("user")).getId(), locale)
                    .orElseThrow(() -> new MasterNotFoundException("master not found"));
        } catch (MasterNotFoundException e) {
            return REDIRECT_MASTER;
        }
        List<ArchiveAppointmentDTO> archive = archiveService.getMasterCommentsOrderByDateTimeDesc(
                master.getId(), locale);
        PageRequest<ArchiveAppointmentDTO> pageRequest = new PageRequest<>(archive);
        request.setAttribute("archive", pageRequest.makePaginatedRequest(request));
        return MASTER_COMMENTS_PAGE;
    }
}
