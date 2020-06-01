package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.exceptions.InvalidDataException;
import net.ukr.lina_chen.exceptions.UserExistsException;
import net.ukr.lina_chen.model.entity.User;
import net.ukr.lina_chen.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_LOGIN;
import static net.ukr.lina_chen.controller.utility.PagesContainer.REGISTRATION_PAGE;


public class RegistrationCommand implements Command {
    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = CommandUtility.geLocale(request);
        if (!Optional.ofNullable(request.getParameter("email")).isPresent()) {
            return REGISTRATION_PAGE;
        }
        try {
            User user = userService.extractUserFromRequest(request);
            userService.saveNewUser(user, locale);
            logger.info("New user {} was successfully registered", user.getEmail());
        } catch (UserExistsException e) {
            logger.error(e);
            request.setAttribute("user_exists", true);
            return REGISTRATION_PAGE;
        } catch (InvalidDataException |SQLException e) {
            logger.error(e);
            return REGISTRATION_PAGE;
        }
        return REDIRECT_LOGIN;
    }
}
