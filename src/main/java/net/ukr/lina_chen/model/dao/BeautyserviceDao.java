package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.BeautyService;

import java.util.List;

public interface BeautyserviceDao extends GenericDao<BeautyService> {
    String QUERY_FIND_ALL = "select * from beautyservices left join professions using (profession_id)";
    String QUERY_FIND_BY_ID = QUERY_FIND_ALL + " where beautyservice_id = ?";
    String QUERY_FIND_BY_PROFESSION_ID = "select * from beautyservices\n" +
            "left join professions using (profession_id) where profession_id = ?";
    String QUERY_REPLACE = "replace into beautyservices (beautyservice_id, beautyservice_name, price, " +
            "profession_id, beautyservice_name_ukr) values (?, ?, ?, ?, ?)";
    String QUERY_DELETE = "delete from beautyservices where beautyservice_id = ?";

    List<BeautyService> findByProfessionId(Long professionId);
}
