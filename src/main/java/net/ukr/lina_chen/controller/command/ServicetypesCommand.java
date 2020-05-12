package net.ukr.lina_chen.controller.command;

import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.ProfessionDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.entity.Profession;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static net.ukr.lina_chen.controller.utility.PagesContainer.SERVICETYPES_PAGE;

public class ServicetypesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        DaoFactory factory = DaoFactory.getInstance();
        try (ProfessionDao professionDao = factory.createProfessionDao()) {
            List<Profession> professions = professionDao.findAll();
            request.setAttribute("servicetypes", professions);
        }
        return SERVICETYPES_PAGE;
    }
}
