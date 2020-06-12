package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.service.ArchiveService;
import net.ukr.lina_chen.model.service.MailService;
import net.ukr.lina_chen.model.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class ProvidedCommand implements Command {
    private final TransactionService transactionService;
    private final MailService mailService;
    private final ArchiveService archiveService;

    private Logger logger = LogManager.getLogger(ProvidedCommand.class);

    public ProvidedCommand(TransactionService transactionService, MailService mailService, ArchiveService archiveService) {
        this.transactionService = transactionService;
        this.mailService = mailService;
        this.archiveService = archiveService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Role roles = (Role) request.getSession().getAttribute("roles");
        Locale locale = CommandUtility.geLocale(request);
        Long archiveAppointmentId = 0L;
        try {
            archiveAppointmentId = transactionService.archiveAppointment(
                    Long.parseLong(request.getParameter("appointmentId")), locale);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return roles.equals(Role.ADMIN) ? REDIRECT_ADMIN_APPOINTMENTS + ERROR_PARAM
                    : REDIRECT_MASTER_APPOINTMENTS + ERROR_PARAM;
        }
        String email = archiveService.getById(archiveAppointmentId, locale).getUser().getEmail();
        mailService.sendEmail(email, archiveAppointmentId);
        return roles.equals(Role.ADMIN) ? REDIRECT_ADMIN_APPOINTMENTS : REDIRECT_MASTER_APPOINTMENTS;
    }
}
