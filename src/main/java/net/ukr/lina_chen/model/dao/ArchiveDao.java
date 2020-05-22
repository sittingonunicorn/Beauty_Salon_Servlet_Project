package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.ArchiveAppointment;

import java.util.List;

public interface ArchiveDao extends GenericDao<ArchiveAppointment> {
    String QUERY_FIND_ALL = "select * from beautyservices inner join archive_appointment as a using (beautyservice_id) \n" +
            "left join masters using (master_id) \n" +
            "left join professions on masters.profession_id=professions.profession_id  \n" +
            "left join users as u on a.user_id=u.user_id";
    String QUERY_FIND_BY_ID = QUERY_FIND_ALL + " where appointment_id = ?";
    String QUERY_REPLACE = "replace into archive_appointment (master_id, user_id, beautyservice_id, \n" +
            "time, date, provided, comment) values (?, ?, ?, ?, ?, ?, ?)";
    String QUERY_DELETE = "delete from archive_appointment where appointment_id = ?";
    String QUERY_FIND_ALL_COMMENTS = "select * from beautyservices inner join archive_appointment as a using (beautyservice_id) \n" +
            "left join masters using (master_id) \n" +
            "left join professions on masters.profession_id=professions.profession_id  \n" +
            "left join users as u on a.user_id=u.user_id where comment is not null ";
    String QUERY_FIND_COMMENTS_BY_MASTER_ID = QUERY_FIND_ALL_COMMENTS + " and master_id = ?";
    String QUERY_FIND_BY_USER_ID = QUERY_FIND_ALL + " where u.user_id = ?";
    String QUERY_UPDATE_COMMENT = "update archive_appointment set comment = ? where appointment_id = ?";
    String QUERY_GET_ID = "select appointment_id from archive_appointment \n" +
            " where appointment_id = LAST_INSERT_ID()";

    List<ArchiveAppointment> getMasterComments(Long masterId);

    List<ArchiveAppointment> getUserArchiveAppointments(Long userId);

    void setComment (String comment, Long appointmentId);

    List<ArchiveAppointment> getAllComments();
}
