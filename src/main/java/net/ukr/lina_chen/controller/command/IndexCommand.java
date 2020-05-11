package net.ukr.lina_chen.controller.command;

import javax.servlet.http.HttpServletRequest;

import static net.ukr.lina_chen.controller.utility.PagesContainer.INDEX_PAGE;

public class IndexCommand implements Command {
    @Override
    public String execute(HttpServletRequest request){
        return INDEX_PAGE;
    }
}
