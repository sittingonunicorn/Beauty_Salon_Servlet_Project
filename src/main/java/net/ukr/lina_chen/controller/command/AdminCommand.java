package net.ukr.lina_chen.controller.command;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.ADMIN_MAIN;

public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return ADMIN_MAIN;
    }
}
