package net.ukr.lina_chen.model.entity;

import java.io.Serializable;
import java.util.Set;

public class Profession implements Serializable {
    private Long id;
    private String name;
    private transient Set<Master> masters;
    private String beautyservicesType;
    private String nameUkr;
    private String beautyservicesTypeUkr;

    public Profession() {
    }

    public Profession(Long id, String name, Set<Master> masters, String beautyservicesType,
                      String nameUkr, String beautyservicesTypeUkr) {
        this.id = id;
        this.name = name;
        this.masters = masters;
        this.beautyservicesType = beautyservicesType;
        this.nameUkr = nameUkr;
        this.beautyservicesTypeUkr = beautyservicesTypeUkr;
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

    public Set<Master> getMasters() {
        return masters;
    }

    public void setMasters(Set<Master> masters) {
        this.masters = masters;
    }

    public String getBeautyservicesType() {
        return beautyservicesType;
    }

    public void setBeautyservicesType(String beautyservicesType) {
        this.beautyservicesType = beautyservicesType;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    public String getBeautyservicesTypeUkr() {
        return beautyservicesTypeUkr;
    }

    public void setBeautyservicesTypeUkr(String beautyservicesTypeUkr) {
        this.beautyservicesTypeUkr = beautyservicesTypeUkr;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beautyservicesType='" + beautyservicesType + '\'' +
                ", nameUkr='" + nameUkr + '\'' +
                ", beautyservicesTypeUkr='" + beautyservicesTypeUkr + '\'' +
                '}';
    }

    public static class ProfessionBuilder
    {
        private Profession profession;

        private ProfessionBuilder()
        {
            profession = new Profession();
        }

        public ProfessionBuilder withId(Long id)
        {
            profession.id = id;
            return this;
        }

        public ProfessionBuilder withName(String name)
        {
            profession.name = name;
            return this;
        }

        public ProfessionBuilder withMasters(Set<Master> masters)
        {
            profession.masters = masters;
            return this;
        }

        public ProfessionBuilder withBeautyservicesType(String beautyservicesType)
        {
            profession.beautyservicesType = beautyservicesType;
            return this;
        }

        public ProfessionBuilder withNameUkr(String nameUkr)
        {
            profession.nameUkr = nameUkr;
            return this;
        }

        public ProfessionBuilder withBeautyservicesTypeUkr(String beautyservicesTypeUkr)
        {
            profession.beautyservicesTypeUkr = beautyservicesTypeUkr;
            return this;
        }

        public static ProfessionBuilder profession()
        {
            return new ProfessionBuilder();
        }

        public Profession build()
        {
            return profession;
        }
    }
}
