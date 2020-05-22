package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.Appointment;

import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment> {
    String QUERY_FIND_ALL = "select * from beautyservices inner join appointment as a using (beautyservice_id) \n" +
            "left join masters using (master_id) \n" +
            "left join professions on masters.profession_id=professions.profession_id  \n" +
            "left join users as u on a.user_id=u.user_id";
    String QUERY_FIND_BY_ID = QUERY_FIND_ALL + " where appointment_id = ?";
    String QUERY_REPLACE = "replace into appointment (master_id, user_id, beautyservice_id, \n" +
            "time, date, provided) values (?, ?, ?, ?, ?, ?)";
    String QUERY_DELETE = "delete from appointment where appointment_id = ?";
    String QUERY_FIND_BY_MASTER_ID = QUERY_FIND_ALL + " where master_id = ?";
    String QUERY_UPDATE_SET_PROVIDED = "update appointment set provided = true where appointment_id = ?";
    String QUERY_GET_ID = "select appointment_id from appointment \n" +
            " where appointment_id = LAST_INSERT_ID()";

    List<Appointment> getMasterAppointments(Long masterId);

    void setProvided (Long appointmentId);
//TODO master's user mapping

//    select * from beautyservices left join appointment as a using (beautyservice_id) left join masters as m using (master_id)
//    left join users as u on a.user_id=u.user_id left join users as us on m.user_id=us.user_id where appointment_id = 55;
}
