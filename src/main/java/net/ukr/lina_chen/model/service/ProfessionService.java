package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.ProfessionDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.entity.Profession;

import java.util.List;

public class ProfessionService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<Profession> getAllServicetypes() {
        List<Profession> professions;
        try (ProfessionDao professionDao = factory.createProfessionDao()) {
            professions = professionDao.findAllServicetypes();
        }
        return professions;
    }

    public Profession getById (Long professionId){
        Profession profession;
        try (ProfessionDao professionDao = factory.createProfessionDao()) {
            profession = professionDao.findById(professionId);
        }
        return profession;
    }
}
