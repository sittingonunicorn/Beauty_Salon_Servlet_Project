package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.model.dto.ArchiveAppointmentDTO;
import net.ukr.lina_chen.model.dto.MasterDTO;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.service.ArchiveService;
import net.ukr.lina_chen.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.MASTER_COMMENTS_PAGE;

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
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("0"));
        Optional<MasterDTO> master = masterService.getByUserId(
                ((UserDTO) request.getSession().getAttribute("user")).getId(), locale);
        List<ArchiveAppointmentDTO> archive = archiveService.getMasterCommentsOrderByDateTimeDesc(
                master.get().getId(), locale);
        PageRequest<ArchiveAppointmentDTO> pageRequest = new PageRequest<>(archive);
        archive = pageRequest.getPage(page);
        request.setAttribute("archive", archive);
        request.setAttribute("pageNumbers", pageRequest.getPageNumbers());
        return MASTER_COMMENTS_PAGE;
    }
}
