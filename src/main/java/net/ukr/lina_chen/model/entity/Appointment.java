package net.ukr.lina_chen.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private Long id;
    private Master master;
    private User user;
    private BeautyService beautyService;
    private LocalTime time;
    private LocalDate date;
    private boolean provided;

    public Appointment(Long id, Master master, User user, BeautyService beautyService,
                       LocalTime time, LocalDate date, boolean provided) {
        this.id = id;
        this.master = master;
        this.user = user;
        this.beautyService = beautyService;
        this.time = time;
        this.date = date;
        this.provided = provided;
    }

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BeautyService getBeautyService() {
        return beautyService;
    }

    public void setBeautyService(BeautyService beautyService) {
        this.beautyService = beautyService;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isProvided() {
        return provided;
    }

    public void setProvided(boolean provided) {
        this.provided = provided;
    }

    public static class AppointmentBuilder {
        private final Appointment appointment;

        private AppointmentBuilder() {
            appointment = new Appointment();
        }

        public AppointmentBuilder withId(Long id) {
            appointment.id = id;
            return this;
        }

        public AppointmentBuilder withMaster(Master master) {
            appointment.master = master;
            return this;
        }

        public AppointmentBuilder withUser(User user) {
            appointment.user = user;
            return this;
        }

        public AppointmentBuilder withBeautyService(BeautyService beautyService) {
            appointment.beautyService = beautyService;
            return this;
        }

        public AppointmentBuilder withTime(LocalTime time) {
            appointment.time = time;
            return this;
        }

        public AppointmentBuilder withDate(LocalDate date) {
            appointment.date = date;
            return this;
        }

        public AppointmentBuilder withProvided(boolean provided) {
            appointment.provided = provided;
            return this;
        }

        public static AppointmentBuilder appointment() {
            return new AppointmentBuilder();
        }

        public Appointment build() {
            return appointment;
        }
    }
}
