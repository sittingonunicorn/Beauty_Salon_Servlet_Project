package net.ukr.lina_chen.controller.command;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.REDIRECT_MASTER_APPOINTMENTS;

public class MasterProvidedCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return REDIRECT_MASTER_APPOINTMENTS;
    }
}
