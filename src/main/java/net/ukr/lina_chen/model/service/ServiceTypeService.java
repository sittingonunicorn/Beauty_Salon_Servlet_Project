package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.ServiceTypeDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.ServiceTypeDTO;
import net.ukr.lina_chen.model.entity.ServiceType;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ServiceTypeService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public List<ServiceTypeDTO> getAllServicetypes(Locale locale) {
        List<ServiceType> serviceTypes;
        try (ServiceTypeDao serviceTypeDao = factory.createServiceTypeDao(locale)) {
            serviceTypes = serviceTypeDao.findAllServicetypes();
        }
        return serviceTypes.stream()
                .map(p -> ServiceTypeDTO.ServiceTypeDTOBuilder.professionDTO()
                        .withId(p.getId())
                        .withBeautyservicesType(p.getBeautyservicesType())
                        .build())
                .collect(Collectors.toList());
    }

    public ServiceType getById(Long professionId, Locale locale) {
        ServiceType serviceType;
        try (ServiceTypeDao serviceTypeDao = factory.createServiceTypeDao(locale)) {
            serviceType = serviceTypeDao.findById(professionId);
        }
        return serviceType;
    }
}
