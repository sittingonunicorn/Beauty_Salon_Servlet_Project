package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.Master;

public interface MasterDao extends GenericDao<Master>{
    String SQL_FIND_ALL = "select * from masters left join users using (user_id)";
    String SQL_FIND_BY_ID = "select * from masters where master_id = ? left join users using (user_id)";
//    String SQL_FIND_BY_EMAIL = SQL_FIND_ALL + " where email = ?";
    String SQL_REPLACE = "replace into masters (user_id, time_begin, time_end, profession_id) values (?, ?, ?, ?)";
    String SQL_DELETE = "delete from masters where masters_id = ?";
}
