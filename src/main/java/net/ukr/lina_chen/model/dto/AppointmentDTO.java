package net.ukr.lina_chen.model.dto;


import java.time.LocalTime;

public class AppointmentDTO {
    private Long id;
    private String masterName;
    private String userName;
    private String beautyService;
    private LocalTime time;
    private String date;
    private boolean provided;

    public AppointmentDTO(Long id, String masterName, String userName,
                          String beautyService, LocalTime time, String date, boolean provided) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public static class AppointmentDTOBuilder {
        private AppointmentDTO appointmentDTO;

        private AppointmentDTOBuilder() {
            appointmentDTO = new AppointmentDTO();
        }

        public AppointmentDTOBuilder withId(Long id) {
            appointmentDTO.id = id;
            return this;
        }

        public AppointmentDTOBuilder withMasterName(String masterName) {
            appointmentDTO.masterName = masterName;
            return this;
        }

        public AppointmentDTOBuilder withUserName(String userName) {
            appointmentDTO.userName = userName;
            return this;
        }

        public AppointmentDTOBuilder withBeautyService(String beautyService) {
            appointmentDTO.beautyService = beautyService;
            return this;
        }

        public AppointmentDTOBuilder withTime(LocalTime time) {
            appointmentDTO.time = time;
            return this;
        }

        public AppointmentDTOBuilder withDate(String date) {
            appointmentDTO.date = date;
            return this;
        }

        public AppointmentDTOBuilder withProvided(boolean provided) {
            appointmentDTO.provided = provided;
            return this;
        }

        public static AppointmentDTOBuilder appointmentDTO() {
            return new AppointmentDTOBuilder();
        }

        public AppointmentDTO build() {
            return appointmentDTO;
        }
    }
}
