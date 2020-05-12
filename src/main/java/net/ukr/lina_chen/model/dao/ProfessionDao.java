package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.Profession;

public interface ProfessionDao extends GenericDao<Profession> {
    String SQL_FIND_ALL = "select * from professions \n" +
            "left join masters using (profession_id) left join users using (user_id)";
    String SQL_FIND_BY_ID = "select * from professions where profession_id = ? \n" +
            "left join masters using (profession_id) left join users using (user_id)";
    String SQL_REPLACE = "replace into professions (profession_name, beautyservices_type, profession_name_ukr, " +
            "beautyservices_type_ukr) values (?, ?, ?, ?)";
    String SQL_DELETE = "delete from professions where profession_id = ?";
}
