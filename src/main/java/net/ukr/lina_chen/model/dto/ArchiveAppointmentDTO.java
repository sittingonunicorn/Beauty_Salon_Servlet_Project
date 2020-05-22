package net.ukr.lina_chen.model.dto;


public class ArchiveAppointmentDTO extends AppointmentDTO {
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArchiveAppointmentDTO(AppointmentDTO appointment, String comment) {
        super(appointment.getId(), appointment.getMasterName(), appointment.getUserName(),
                appointment.getBeautyService(), appointment.getTime(), appointment.getDate(), appointment.isProvided());
        this.comment = comment;
    }

    public ArchiveAppointmentDTO() {
    }
}
