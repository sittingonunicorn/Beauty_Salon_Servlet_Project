package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dao.factory.JDBCMasterDao;
import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.entity.User;
import net.ukr.lina_chen.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.equals("") || password == null || password.equals("")) {
            return LOGIN_PAGE;
        }

        Optional<User> user = userService.getUserByEmailAndPassword(email, password);
        if (user.isPresent()) {
            if (CommandUtility.checkUserIsLogged(request, email)) {
                return ERROR_PAGE;
            }
            request.getSession().setAttribute("user", user.get());
            if (user.get().getRole().equals(Role.ADMIN)) {
                CommandUtility.setUserRole(request, Role.ADMIN, email);
                return REDIRECT_ADMIN;
            } else if (user.get().getRole().equals(Role.USER)) {
                CommandUtility.setUserRole(request, Role.USER, email);
                return REDIRECT_USER;
            } else if (user.get().getRole().equals(Role.MASTER)) {
                CommandUtility.setUserRole(request, Role.MASTER, email);
                return REDIRECT_MASTER;
            }
        }
        CommandUtility.setUserRole(request, Role.GUEST, email);
        return REDIRECT_LOGIN;
    }

}
