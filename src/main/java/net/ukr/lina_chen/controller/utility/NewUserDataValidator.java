package net.ukr.lina_chen.controller.utility;

import net.ukr.lina_chen.exceptions.InvalidDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static net.ukr.lina_chen.controller.utility.RegexContainer.*;

public class NewUserDataValidator {
    private static final Logger logger = LogManager.getLogger(NewUserDataValidator.class);

    public void validateUser(HttpServletRequest request) throws InvalidDataException {
        List<String> validation = new ArrayList<>();
        if (!request.getParameter("email").matches(EMAIL_REGEX)){
            validation.add("wrongEmailFormat");
        }
        if (!request.getParameter("name").matches(NAME_REGEX_LAT)){
            validation.add("wrongNameFormat");
        }
        if(! request.getParameter("nameUkr").matches(NAME_REGEX_UA)){
            validation.add("wrongNameUkrFormat");
        }
        if(!request.getParameter("password").matches(PASSWORD_REGEX)){
            validation.add("wrongPasswordFormat");
        }

        if (!validation.isEmpty()){
            validation.forEach(logger::info);
            validation.forEach( o -> request.setAttribute(String.valueOf(o), true));
            throw new InvalidDataException();
        }

    }
}
