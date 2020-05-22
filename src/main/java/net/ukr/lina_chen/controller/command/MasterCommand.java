package net.ukr.lina_chen.controller.command;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.MASTER_MAIN;

public class MasterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return MASTER_MAIN;
    }
}