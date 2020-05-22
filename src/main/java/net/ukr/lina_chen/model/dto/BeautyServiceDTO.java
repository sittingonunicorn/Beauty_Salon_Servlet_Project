package net.ukr.lina_chen.model.dto;

import java.math.BigDecimal;

public class BeautyServiceDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long professionId;

    public BeautyServiceDTO(Long id, String name, BigDecimal price, Long professionId) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }
}
