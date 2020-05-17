package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.Profession;

import java.util.List;

public interface ProfessionDao extends GenericDao<Profession> {
    String QUERY_FIND_ALL = "select * from professions \n" +
            "left join masters using (profession_id) left join users using (user_id)";
    String QUERY_FIND_BY_ID = QUERY_FIND_ALL + " where profession_id = ?";
    String QUERY_REPLACE = "replace into professions (profession_name, beautyservices_type, profession_name_ukr, \n" +
            "beautyservices_type_ukr) values (?, ?, ?, ?)";
    String QUERY_DELETE = "delete from professions where profession_id = ?";

    List<Profession> findAllServicetypes();
}
