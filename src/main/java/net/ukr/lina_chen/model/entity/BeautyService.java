package net.ukr.lina_chen.model.entity;

import java.math.BigDecimal;

public class BeautyService {

    private Long id;
    private String name;
    private BigDecimal price;
    private Profession profession;
    private String nameUkr;

    public BeautyService() {
    }

    public BeautyService(Long id, String name, BigDecimal price, Profession profession, String nameUkr) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.profession = profession;
        this.nameUkr = nameUkr;
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

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }
}
