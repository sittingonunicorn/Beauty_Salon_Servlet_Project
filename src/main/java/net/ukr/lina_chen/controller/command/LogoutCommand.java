package net.ukr.lina_chen.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static net.ukr.lina_chen.controller.utility.PagesContainer.LOGOUT_PARAM;
import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_LOGIN;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.removeUserFromSession(request);
        final HttpSession session = request.getSession();
        session.invalidate();
        return REDIRECT_LOGIN + LOGOUT_PARAM;
    }
}
