package net.ukr.lina_chen.model.entity;

import java.io.Serializable;
import java.util.Set;

public class ServiceType implements Serializable {
    private Long id;
    private transient Set<Master> masters;
    private transient Set<BeautyService> beautyServices;
    private String beautyservicesType;
    private String beautyservicesTypeUkr;

    public ServiceType() {
    }

    public ServiceType(Long id, Set<Master> masters, Set<BeautyService> beautyServices, String beautyservicesType,
                       String beautyservicesTypeUkr) {
        this.id = id;
        this.masters = masters;
        this.beautyServices = beautyServices;
        this.beautyservicesType = beautyservicesType;
        this.beautyservicesTypeUkr = beautyservicesTypeUkr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBeautyservicesTypeUkr() {
        return beautyservicesTypeUkr;
    }

    public void setBeautyservicesTypeUkr(String beautyservicesTypeUkr) {
        this.beautyservicesTypeUkr = beautyservicesTypeUkr;
    }

    public Set<BeautyService> getBeautyServices() {
        return beautyServices;
    }

    public void setBeautyServices(Set<BeautyService> beautyServices) {
        this.beautyServices = beautyServices;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "id=" + id +
                ", beautyservicesType='" + beautyservicesType + '\'' +
                ", beautyservicesTypeUkr='" + beautyservicesTypeUkr + '\'' +
                '}';
    }

    public static class ServiceTypeBuilder {
        private ServiceType serviceType;

        private ServiceTypeBuilder() {
            serviceType = new ServiceType();
        }

        public ServiceTypeBuilder withId(Long id) {
            serviceType.id = id;
            return this;
        }

        public ServiceTypeBuilder withMasters(Set<Master> masters) {
            serviceType.masters = masters;
            return this;
        }

        public ServiceTypeBuilder withBeautyServices(Set<BeautyService> beautyServices) {
            serviceType.beautyServices = beautyServices;
            return this;
        }

        public ServiceTypeBuilder withBeautyservicesType(String beautyservicesType) {
            serviceType.beautyservicesType = beautyservicesType;
            return this;
        }

        public ServiceTypeBuilder withBeautyservicesTypeUkr(String beautyservicesTypeUkr) {
            serviceType.beautyservicesTypeUkr = beautyservicesTypeUkr;
            return this;
        }

        public static ServiceTypeBuilder serviceType() {
            return new ServiceTypeBuilder();
        }

        public ServiceType build() {
            return serviceType;
        }
    }
}
