package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.Master;


public interface MasterDao extends GenericDao<Master>{
    String SQL_FIND_ALL = "select * from professions \n" +
            "left join masters using (profession_id) left join users using (user_id)";
    String SQL_FIND_BY_ID =SQL_FIND_ALL + " where master_id = ?";
    String SQL_REPLACE = "replace into masters (user_id, time_begin, time_end, profession_id) values (?, ?, ?, ?)";
    String SQL_DELETE = "delete from masters where masters_id = ?";

}
