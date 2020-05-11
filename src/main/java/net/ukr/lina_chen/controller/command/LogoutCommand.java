package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_LOGIN;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.removeUserFromSession(request);
        final HttpSession session = request.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle("messages",
                new Locale(Optional.ofNullable((String) session.getAttribute("lang"))
                        .orElse("en")));
        session.invalidate();
        CommandUtility.setUserRole(request, Role.GUEST, "Guest");
        request.setAttribute("logout", bundle.getString("message.logged.out"));
        return REDIRECT_LOGIN;
    }
}
