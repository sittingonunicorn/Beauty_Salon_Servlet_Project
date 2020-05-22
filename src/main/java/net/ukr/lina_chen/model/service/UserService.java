package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.controller.utility.NewUserDataValidator;
import net.ukr.lina_chen.exceptions.InvalidUserDataException;
import net.ukr.lina_chen.exceptions.UserExistsException;
import net.ukr.lina_chen.model.dao.UserDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.UserDTO;
import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserService {
    private final DaoFactory factory = DaoFactory.getInstance();
    private final NewUserDataValidator validator;

    public UserService() {
        this.validator = new NewUserDataValidator();

    }

    public Optional<UserDTO> getUserByEmailAndPassword(String email, String password, boolean isLocaleEn) {
        try (UserDao userDao = factory.createUserDao()) {
            Optional<User> user = userDao.findUserByEmail(email);
            if (user.isPresent() && BCrypt.checkpw(password,user.get().getPassword())) {
                return Optional.of(UserDTO.Builder.anUserDTO()
                        .withEmail(user.get().getEmail())
                        .withPassword(user.get().getPassword())
                        .withRoles(user.get().getRoles())
                        .withName(isLocaleEn? user.get().getName(): user.get().getNameUkr())
                        .withId(user.get().getId())
                        .build());
            } else {
                return Optional.empty();
            }
        }
    }

    public boolean userExists(String email) {
        try (UserDao userDao = factory.createUserDao()) {
            return userDao.findUserByEmail(email).isPresent();
        }
    }

    public void saveNewUser(User user) throws SQLException, UserExistsException {
        if (userExists(user.getEmail())) {
            throw new UserExistsException("User with such email already exists");
        }
        try (UserDao userDao = factory.createUserDao()) {
            userDao.create(user);
        }
    }

    public User extractUserFromRequest(HttpServletRequest request) throws InvalidUserDataException {
        validator.validateUser(request);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        return User.Builder.anUser()
                .withEmail(request.getParameter("email"))
                .withName(request.getParameter("name"))
                .withNameUkr(request.getParameter("nameUkr"))
                .withPassword(BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(10)))
                .withRoles(roles)
                .build();
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> user;
        try (UserDao userDao = factory.createUserDao()) {
            user = Optional.of(userDao.findById(id));
            }
        return user;
        }
    }

