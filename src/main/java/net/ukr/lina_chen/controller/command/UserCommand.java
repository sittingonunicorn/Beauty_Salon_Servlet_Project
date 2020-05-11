package net.ukr.lina_chen.controller.command;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.USER_MAIN;

public class UserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return USER_MAIN;
    }
}