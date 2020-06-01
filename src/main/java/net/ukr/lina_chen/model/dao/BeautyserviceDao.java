package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.BeautyService;

import java.util.List;

public interface BeautyserviceDao extends GenericDao<BeautyService> {
    List<BeautyService> findByProfessionId(Long professionId);
}
