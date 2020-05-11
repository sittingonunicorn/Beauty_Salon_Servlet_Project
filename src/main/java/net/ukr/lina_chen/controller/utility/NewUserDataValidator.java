package net.ukr.lina_chen.controller.utility;

import net.ukr.lina_chen.exceptions.InvalidUserDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.RegexContainer.*;

public class NewUserDataValidator {
    private static final Logger logger = LogManager.getLogger(NewUserDataValidator.class);

    public void validateUser(HttpServletRequest request) throws InvalidUserDataException {
        if (!request.getParameter("email").matches(EMAIL_REGEX)){
            logger.info("email");
        }
        if (!request.getParameter("name").matches(NAME_REGEX_LAT)){
            logger.info("lat");
        }
        if(! request.getParameter("nameUkr").matches(NAME_REGEX_UA))
        {
            logger.info("ukr");
        }
        if(!request.getParameter("password").matches(PASSWORD_REGEX)){
            logger.info("password");
        }

        if (request.getParameter("email").matches(EMAIL_REGEX) &&
                request.getParameter("name").matches(NAME_REGEX_LAT) &&
                request.getParameter("nameUkr").matches(NAME_REGEX_UA) &&
                request.getParameter("password").matches(PASSWORD_REGEX)) {
            logger.info("valid user data");
        } else {
            throw new InvalidUserDataException("Invalid data");
        }

    }
}
