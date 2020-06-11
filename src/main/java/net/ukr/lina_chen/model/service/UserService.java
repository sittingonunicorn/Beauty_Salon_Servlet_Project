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
import java.util.*;

public class UserService {
    private final DaoFactory factory = DaoFactory.getInstance();
    private final NewUserDataValidator validator;

    public UserService() {
        this.validator = new NewUserDataValidator();

    }

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

    public boolean userExists(String email, Locale locale) {
        try (UserDao userDao = factory.createUserDao(locale)) {
            return userDao.findUserByEmail(email).isPresent();
        }
    }

    public void saveNewUser(User user, Locale locale) throws SQLException, UserExistsException {
        if (userExists(user.getEmail(), locale)) {
            throw new UserExistsException();
        }
        try (UserDao userDao = factory.createUserDao(locale)) {
            userDao.create(user);
        }
    }

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

    public Optional<User> getUserById(Long id, Locale locale) {
        Optional<User> user;
        try (UserDao userDao = factory.createUserDao(locale)) {
            user = Optional.of(userDao.findById(id));
        }
        return user;
    }
}

