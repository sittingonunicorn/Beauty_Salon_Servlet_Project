package net.ukr.lina_chen.controller.command;


import javax.servlet.http.HttpServletRequest;


import static net.ukr.lina_chen.controller.utility.PagesContainer.*;

public class ErrorCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return ERROR_PAGE;
    }
}
