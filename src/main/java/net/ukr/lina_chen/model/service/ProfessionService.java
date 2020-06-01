package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.ProfessionDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.ProfessionDTO;
import net.ukr.lina_chen.model.entity.Profession;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProfessionService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<ProfessionDTO> getAllServicetypes(Locale locale) {
        List<Profession> professions;
        try (ProfessionDao professionDao = factory.createProfessionDao(ResourceBundle.getBundle("queries", locale))) {
            professions = professionDao.findAllServicetypes();
        }
        return professions.stream()
                .map(p -> ProfessionDTO.ProfessionDTOBuilder.professionDTO()
                        .withId(p.getId())
                        .withBeautyservicesType(p.getBeautyservicesType())
                        .withName(p.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public Profession getById(Long professionId, Locale locale) {
        Profession profession;
        try (ProfessionDao professionDao = factory.createProfessionDao(ResourceBundle.getBundle("queries", locale))) {
            profession = professionDao.findById(professionId);
        }
        return profession;
    }
}
