package net.ukr.lina_chen.controller.command;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.SAVE_PAGE;

public class SaveCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return SAVE_PAGE;
    }
}
