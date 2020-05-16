package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.BeautyserviceDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.entity.BeautyService;

import java.util.List;
import java.util.Optional;

public class BeautyservicesImpl {
    private final DaoFactory factory = DaoFactory.getInstance();


    public List<BeautyService> getBeautyservicesByProfession(Long professionId){
        List<BeautyService> beautyServices;
        try(BeautyserviceDao beautyserviceDao = factory.createBeautyserviceDao()){
            beautyServices=beautyserviceDao.findByProfessionId(professionId);
        }
        return beautyServices;
    }

    public Optional<BeautyService> getById(Long beautyserviceId){
        Optional<BeautyService> beautyService;
        try(BeautyserviceDao beautyserviceDao = factory.createBeautyserviceDao()){
            beautyService=Optional.of(beautyserviceDao.findById(beautyserviceId));
        }
        return beautyService;
    }
}
