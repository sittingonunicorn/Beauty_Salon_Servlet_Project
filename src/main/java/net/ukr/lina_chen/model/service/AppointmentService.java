package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.AppointmentDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.entity.Appointment;


import java.util.List;

public class AppointmentService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<Appointment> getMastersAppointments(Long masterId) {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointments = appointmentDao.getMasterAppointments(masterId);
        }
        return appointments;
    }

    public Appointment getById(Long appointmentId) {
        Appointment appointment;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointment = appointmentDao.findById(appointmentId);
        }
        return appointment;
    }

    public List<Appointment> getAll() {
        List<Appointment> appointments;
        try (AppointmentDao appointmentDao = factory.createAppointmentDao()) {
            appointments = appointmentDao.findAll();
        }
        return appointments;
    }
}
