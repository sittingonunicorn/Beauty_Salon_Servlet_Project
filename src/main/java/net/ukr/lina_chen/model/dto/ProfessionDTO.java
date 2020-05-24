package net.ukr.lina_chen.model.dto;


public class ProfessionDTO {
    private Long id;
    private String name;
    private String beautyservicesType;

    public ProfessionDTO(Long id, String name, String beautyservicesType) {
        this.id = id;
        this.name = name;
        this.beautyservicesType = beautyservicesType;
    }

    public ProfessionDTO() {
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

    public String getBeautyservicesType() {
        return beautyservicesType;
    }

    public void setBeautyservicesType(String beautyservicesType) {
        this.beautyservicesType = beautyservicesType;
    }

    public static class ProfessionDTOBuilder
    {
        private ProfessionDTO professionDTO;

        private ProfessionDTOBuilder()
        {
            professionDTO = new ProfessionDTO();
        }

        public ProfessionDTOBuilder withId(Long id)
        {
            professionDTO.id = id;
            return this;
        }

        public ProfessionDTOBuilder withName(String name)
        {
            professionDTO.name = name;
            return this;
        }

        public ProfessionDTOBuilder withBeautyservicesType(String beautyservicesType)
        {
            professionDTO.beautyservicesType = beautyservicesType;
            return this;
        }

        public static ProfessionDTOBuilder professionDTO()
        {
            return new ProfessionDTOBuilder();
        }

        public ProfessionDTO build()
        {
            return professionDTO;
        }
    }
}
