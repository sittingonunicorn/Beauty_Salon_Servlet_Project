package net.ukr.lina_chen.model.dto;

public class BeautyServiceDTO {
    private Long id;
    private String name;
    private String price;
    private Long professionId;

    public BeautyServiceDTO(Long id, String name, String price, Long professionId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.professionId = professionId;
    }

    public BeautyServiceDTO() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    @Override
    public String toString() {
        return "BeautyServiceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", professionId=" + professionId +
                '}';
    }

    public static class BeautyServiceDTOBuilder
    {
        private BeautyServiceDTO beautyServiceDTO;

        private BeautyServiceDTOBuilder()
        {
            beautyServiceDTO = new BeautyServiceDTO();
        }

        public BeautyServiceDTOBuilder withId(Long id)
        {
            beautyServiceDTO.id = id;
            return this;
        }

        public BeautyServiceDTOBuilder withName(String name)
        {
            beautyServiceDTO.name = name;
            return this;
        }

        public BeautyServiceDTOBuilder withPrice(String price)
        {
            beautyServiceDTO.price = price;
            return this;
        }

        public BeautyServiceDTOBuilder withProfessionId(Long professionId)
        {
            beautyServiceDTO.professionId = professionId;
            return this;
        }

        public static BeautyServiceDTOBuilder beautyServiceDTO()
        {
            return new BeautyServiceDTOBuilder();
        }

        public BeautyServiceDTO build()
        {
            return beautyServiceDTO;
        }
    }
}
