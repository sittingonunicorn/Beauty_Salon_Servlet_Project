package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.ProfessionDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.ProfessionDTO;
import net.ukr.lina_chen.model.entity.Profession;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessionService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<ProfessionDTO> getAllServicetypes(boolean isLocaleEn) {
        List<Profession> professions;
        try (ProfessionDao professionDao = factory.createProfessionDao()) {
            professions = professionDao.findAllServicetypes();
        }
        return professions.stream()
                .map(p-> ProfessionDTO.ProfessionDTOBuilder.professionDTO()
                .withId(p.getId())
                .withBeautyservicesType(isLocaleEn? p.getBeautyservicesType():p.getBeautyservicesTypeUkr())
                .withName(isLocaleEn? p.getName():p.getNameUkr())
                .build())
                .sorted(Comparator.comparing(ProfessionDTO::getBeautyservicesType))
                .collect(Collectors.toList());
    }

    public Profession getById (Long professionId){
        Profession profession;
        try (ProfessionDao professionDao = factory.createProfessionDao()) {
            profession = professionDao.findById(professionId);
        }
        return profession;
    }
}
