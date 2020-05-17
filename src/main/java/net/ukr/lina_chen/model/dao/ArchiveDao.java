package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.ArchiveAppointment;

import java.util.List;

public interface ArchiveDao extends GenericDao<ArchiveAppointment> {
    String QUERY_FIND_ALL = "select * from beautyservices left join archive_appointment as a using (beautyservice_id) \n" +
            "left join masters using (master_id) \n" +
            "left join professions on masters.profession_id=professions.profession_id  \n" +
            "left join users as u on a.user_id=u.user_id";
    String QUERY_FIND_BY_ID = QUERY_FIND_ALL + " where appointment_id = ?";
    String QUERY_REPLACE = "replace into archive_appointment (master_id, user_id, beautyservice_id, \n" +
            "time, date, provided, comment) values (?, ?, ?, ?, ?, ?, ?)";
    String QUERY_DELETE = "delete from archive_appointment where appointment_id = ?";
    String QUERY_FIND_BY_MASTER_ID = QUERY_FIND_ALL + " where master_id = ?";
    String QUERY_FIND_BY_USER_ID = QUERY_FIND_ALL + " where u.user_id = ?";
    String QUERY_UPDATE_COMMENT = "update archive_appointment set comment = ? where appointment_id = ?";

    List<ArchiveAppointment> getMasterArchiveAppointments(Long masterId);

    List<ArchiveAppointment> getUserArchiveAppointments(Long userId);

    void setComment (String comment, Long appointmentId);
}
