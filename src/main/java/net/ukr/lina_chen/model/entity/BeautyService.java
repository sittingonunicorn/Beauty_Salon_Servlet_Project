package net.ukr.lina_chen.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class BeautyService implements Serializable {

    private Long id;
    private String name;
    private BigDecimal price;
    private ServiceType serviceType;
    private String nameUkr;

    public BeautyService() {
    }

    public BeautyService(Long id, String name, BigDecimal price, ServiceType serviceType, String nameUkr) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.serviceType = serviceType;
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

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    @Override
    public String toString() {
        return "BeautyService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", serviceType=" + serviceType +
                ", nameUkr='" + nameUkr + '\'' +
                '}';
    }

    public static class BeautyServiceBuilder {
        private final BeautyService beautyService;

        private BeautyServiceBuilder() {
            beautyService = new BeautyService();
        }

        public BeautyServiceBuilder withId(Long id) {
            beautyService.id = id;
            return this;
        }

        public BeautyServiceBuilder withName(String name) {
            beautyService.name = name;
            return this;
        }

        public BeautyServiceBuilder withPrice(BigDecimal price) {
            beautyService.price = price;
            return this;
        }

        public BeautyServiceBuilder withServiceType(ServiceType serviceType) {
            beautyService.serviceType = serviceType;
            return this;
        }

        public BeautyServiceBuilder withNameUkr(String nameUkr) {
            beautyService.nameUkr = nameUkr;
            return this;
        }

        public static BeautyServiceBuilder beautyService() {
            return new BeautyServiceBuilder();
        }

        public BeautyService build() {
            return beautyService;
        }
    }
}
