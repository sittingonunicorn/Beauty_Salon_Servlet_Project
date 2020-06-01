package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.controller.utility.PageRequest;
import net.ukr.lina_chen.model.dto.ArchiveAppointmentDTO;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.service.ArchiveService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.ARCHIVE_APPOINTMENTS_PAGE;

public class UserArchiveCommand implements Command {
    private final ArchiveService archiveService;

    public UserArchiveCommand(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("0"));
        List<ArchiveAppointmentDTO> archive = archiveService.getUserArchive(
                ((UserDTO) request.getSession().getAttribute("user")).getId(), locale);
        PageRequest<ArchiveAppointmentDTO> pageRequest = new PageRequest<>(archive);
        archive = pageRequest.getPage(page);
        request.setAttribute("archiveForUser", archive);
        request.setAttribute("pageNumbers", pageRequest.getPageNumbers());
        return ARCHIVE_APPOINTMENTS_PAGE;
    }
}
