package net.ukr.lina_chen.model.entity;


public class ArchiveAppointment extends Appointment{

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArchiveAppointment(Appointment appointment, String comment) {
        super(appointment.getId(), appointment.getMaster(), appointment.getUser(), appointment.getBeautyService(),
                appointment.getTime(), appointment.getDate(), appointment.isProvided());
        this.comment = comment;
    }

    public ArchiveAppointment() {
    }

}
