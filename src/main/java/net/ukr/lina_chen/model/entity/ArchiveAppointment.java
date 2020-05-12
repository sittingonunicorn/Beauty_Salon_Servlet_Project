package net.ukr.lina_chen.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ArchiveAppointment extends Appointment{

    private String comment;

    public ArchiveAppointment(Long id, Master master, User user, BeautyService beautyService,
                              LocalTime time, LocalDate date, boolean provided, String comment) {
        super(id, master, user, beautyService, time, date, provided);
        this.comment = comment;
    }
}
