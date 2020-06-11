package net.ukr.lina_chen.model.dto;


public class ServiceTypeDTO {
    private Long id;
    private String beautyservicesType;

    public ServiceTypeDTO(Long id, String beautyservicesType) {
        this.id = id;
        this.beautyservicesType = beautyservicesType;
    }

    public ServiceTypeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeautyservicesType() {
        return beautyservicesType;
    }

    public void setBeautyservicesType(String beautyservicesType) {
        this.beautyservicesType = beautyservicesType;
    }

    public static class ServiceTypeDTOBuilder {
        private ServiceTypeDTO serviceTypeDTO;

        private ServiceTypeDTOBuilder() {
            serviceTypeDTO = new ServiceTypeDTO();
        }

        public ServiceTypeDTOBuilder withId(Long id) {
            serviceTypeDTO.id = id;
            return this;
        }

        public ServiceTypeDTOBuilder withBeautyservicesType(String beautyservicesType) {
            serviceTypeDTO.beautyservicesType = beautyservicesType;
            return this;
        }

        public static ServiceTypeDTOBuilder professionDTO() {
            return new ServiceTypeDTOBuilder();
        }

        public ServiceTypeDTO build() {
            return serviceTypeDTO;
        }
    }
}
