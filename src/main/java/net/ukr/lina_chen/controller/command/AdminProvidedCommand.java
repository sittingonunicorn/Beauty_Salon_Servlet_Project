package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.service.ArchiveService;
import net.ukr.lina_chen.model.service.MailService;
import net.ukr.lina_chen.model.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_ADMIN_APPOINTMENTS;

public class AdminProvidedCommand implements Command {
    private final TransactionService transactionService;
    private final MailService mailService;
    private final ArchiveService archiveService;

    Logger logger = LogManager.getLogger(AdminProvidedCommand.class);

    public AdminProvidedCommand(TransactionService transactionService, MailService mailService, ArchiveService archiveService) {
        this.transactionService = transactionService;
        this.mailService = mailService;
        this.archiveService = archiveService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        Long archiveAppointmentId = 0L;
        try {
            archiveAppointmentId = transactionService.archiveAppointment(
                    Long.parseLong(request.getParameter("appointmentId")));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        String email = archiveService.getById(archiveAppointmentId).getUser().getEmail();
        mailService.sendEmail(email, archiveAppointmentId);
        return REDIRECT_ADMIN_APPOINTMENTS;
    }
}
