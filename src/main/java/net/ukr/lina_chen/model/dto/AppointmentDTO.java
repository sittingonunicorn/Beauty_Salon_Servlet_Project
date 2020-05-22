package net.ukr.lina_chen.model.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
    private Long id;
    private String masterName;
    private String userName;
    private String beautyService;
    private LocalTime time;
    private LocalDate date;
    private boolean provided;

    public AppointmentDTO(Long id, String masterName, String userName,
                          String beautyService, LocalTime time, LocalDate date, boolean provided) {
        this.id = id;
        this.masterName = masterName;
        this.userName = userName;
        this.beautyService = beautyService;
        this.time = time;
        this.date = date;
        this.provided = provided;
    }

    public AppointmentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBeautyService() {
        return beautyService;
    }

    public void setBeautyService(String beautyService) {
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

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "id=" + id +
                ", masterName='" + masterName + '\'' +
                ", userName='" + userName + '\'' +
                ", beautyService='" + beautyService + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", provided=" + provided +
                '}';
    }
}