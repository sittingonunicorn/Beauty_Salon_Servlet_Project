package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.Master;

import java.util.List;


public interface MasterDao extends GenericDao<Master>{

    Master findByUserId(Long userId);

    List<Master> findByServiceTypeId(Long professionId);

}
