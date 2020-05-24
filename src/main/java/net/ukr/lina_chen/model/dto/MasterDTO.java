package net.ukr.lina_chen.model.dto;

import java.time.LocalTime;

public class MasterDTO {
    private Long id;
    private String name;
    private LocalTime timeBegin;
    private LocalTime timeEnd;

    public MasterDTO(Long id, String name, LocalTime timeBegin, LocalTime timeEnd) {
        this.id = id;
        this.name = name;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }

    public MasterDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "MasterDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeBegin=" + timeBegin +
                ", timeEnd=" + timeEnd +
                '}';
    }

    public static class MasterDTOBuilder
    {
        private MasterDTO masterDTO;

        private MasterDTOBuilder()
        {
            masterDTO = new MasterDTO();
        }

        public MasterDTOBuilder withId(Long id)
        {
            masterDTO.id = id;
            return this;
        }

        public MasterDTOBuilder withName(String name)
        {
            masterDTO.name = name;
            return this;
        }

        public MasterDTOBuilder withTimeBegin(LocalTime timeBegin)
        {
            masterDTO.timeBegin = timeBegin;
            return this;
        }

        public MasterDTOBuilder withTimeEnd(LocalTime timeEnd)
        {
            masterDTO.timeEnd = timeEnd;
            return this;
        }

        public static MasterDTOBuilder masterDTO()
        {
            return new MasterDTOBuilder();
        }

        public MasterDTO build()
        {
            return masterDTO;
        }
    }
}
