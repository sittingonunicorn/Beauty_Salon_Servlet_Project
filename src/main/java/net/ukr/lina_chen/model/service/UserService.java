package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.controller.utility.NewUserDataValidator;
import net.ukr.lina_chen.exceptions.InvalidDataException;
import net.ukr.lina_chen.exceptions.UserExistsException;
import net.ukr.lina_chen.model.dao.UserDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

/**
 * Class deals with JDBC for actions with User entity. Connects with database through DaoFactory.
 * @author Lina Chentsova
 */
public class UserService {
    /** Private final field factory. Getting instance of DaoFactory to get connection to database */
    private final DaoFactory factory = DaoFactory.getInstance();
    /** Validator to check data before saving new user to database */
    private final NewUserDataValidator validator;

    /**Constructor without params. Getting instance of NewUserDataValidator.*/
    public UserService() {
        this.validator = new NewUserDataValidator();
    }

    /**
     * Method for authentication of User. It gets user data from database by email and then checks with BCrypt
     * if password matches.
     * @param email - user's email.
     * @param password - user's password.
     * @param locale - current locale to get localized query,
     * @return userDTO entity wrapped in Optional or empty Optional, if  there is either no such email found or
     * user's entered a wrong password
     */
    public Optional<UserDTO> getUserByEmailAndPassword(String email, String password, Locale locale) {
        try (UserDao userDao = factory.createUserDao(locale)) {
            Optional<User> user = userDao.findUserByEmail(email);
            if (user.isPresent() && BCrypt.checkpw(password, user.get().getPassword())) {
                return Optional.of(UserDTO.Builder.anUserDTO()
                        .withEmail(user.get().getEmail())
                        .withPassword(user.get().getPassword())
                        .withRoles(user.get().getRole())
                        .withName(user.get().getName())
                        .withId(user.get().getId())
                        .build());
            } else {
                return Optional.empty();
            }
        }
    }

    /**
     * Method is to check if user with entered email exists in database.
     * @param email - user's email.
     * @param locale- current locale to get localized query.
     * @return boolean true if user with this email exists and false - if not.
     */
    public boolean userExists(String email, Locale locale) {
        try (UserDao userDao = factory.createUserDao(locale)) {
            return userDao.findUserByEmail(email).isPresent();
        }
    }

    /**
     * Method to save new user to database after registration. First checks if user with such email
     * already exists in database
     * @param user - user entity.
     * @param locale - current locale to get localized query.
     * @throws UserExistsException if user with this email already exists in database.
     */
    public void saveNewUser(User user, Locale locale) throws UserExistsException, SQLException {
        if (userExists(user.getEmail(), locale)) {
            throw new UserExistsException();
        }
        try (UserDao userDao = factory.createUserDao(locale)) {
            userDao.create(user);
        }
    }

    /**
     * Method to extract user's data from registration form, validate them, code the password with BCrypt
     * and create user entity.
     * @param request - request to get user data from it.
     * @return user entity with user data.
     * @throws InvalidDataException if user data don't pass the validation.
     */
    public User extractUserFromRequest(HttpServletRequest request) throws InvalidDataException {
        validator.validateUser(request);
        return User.Builder.anUser()
                .withEmail(request.getParameter("email"))
                .withName(request.getParameter("name"))
                .withNameUkr(request.getParameter("nameUkr"))
                .withPassword(BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(10)))
                .withRole(Role.USER)
                .build();
    }


    /**
     * Method to get user entity from database by id.
     * @param id - user id.
     * @param locale - current locale to get localized query.
     * @return user entity wrapped in Optional.
     */
    public Optional<User> getUserById(Long id, Locale locale) {
        Optional<User> user;
        try (UserDao userDao = factory.createUserDao(locale)) {
            user = Optional.of(userDao.findById(id));
        }
        return user;
    }
}

