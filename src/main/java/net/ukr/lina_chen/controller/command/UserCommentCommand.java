package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.ArchiveAppointment;
import net.ukr.lina_chen.model.entity.User;
import net.ukr.lina_chen.model.service.ArchiveService;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_ARCHIVE_APPOINTMENTS;
import static net.ukr.lina_chen.controller.utility.PagesContainer.USER_COMMENT_PAGE;

public class UserCommentCommand implements Command {
    private final ArchiveService archiveService;

    public UserCommentCommand(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long appointmentId = Long.parseLong(request.getParameter("appointmentId"));
        request.setAttribute("appointment",
                archiveService.getByIdLocalized(appointmentId, CommandUtility.isLocaleEn(request)));
        if (!Optional.ofNullable(request.getParameter("newComment")).isPresent()) {
            return USER_COMMENT_PAGE;
        }

        archiveService.setComment(request.getParameter("newComment"), appointmentId);
        return REDIRECT_ARCHIVE_APPOINTMENTS;
    }
}
