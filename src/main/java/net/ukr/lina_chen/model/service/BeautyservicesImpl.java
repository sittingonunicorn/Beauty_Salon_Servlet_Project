package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.BeautyserviceDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.BeautyServiceDTO;
import net.ukr.lina_chen.model.entity.BeautyService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BeautyservicesImpl {
    private final DaoFactory factory = DaoFactory.getInstance();


    public List<BeautyServiceDTO> getBeautyservicesByProfession(Long professionId, boolean isLocaleEn){
        List<BeautyService> beautyServices;
        try(BeautyserviceDao beautyserviceDao = factory.createBeautyserviceDao()){
            beautyServices=beautyserviceDao.findByProfessionId(professionId);
        }
        return beautyServices.stream().map(b -> getLocalizedDTO(isLocaleEn, b))
                .sorted(Comparator.comparing(BeautyServiceDTO::getName))
                .collect(Collectors.toList());
    }

    public Optional<BeautyService> getById(Long beautyserviceId){
        Optional<BeautyService> beautyService;
        try(BeautyserviceDao beautyserviceDao = factory.createBeautyserviceDao()){
            beautyService=Optional.of(beautyserviceDao.findById(beautyserviceId));
        }
        return beautyService;
    }

    private BeautyServiceDTO getLocalizedDTO (boolean isLocaleEn, BeautyService beautyService){
        return BeautyServiceDTO.BeautyServiceDTOBuilder.beautyServiceDTO()
                .withId(beautyService.getId())
                .withName(isLocaleEn? beautyService.getName():beautyService.getNameUkr())
                .withPrice(isLocaleEn? getPriceEn(beautyService.getPrice()):getPriceUa(beautyService.getPrice()))
                .withProfessionId(beautyService.getProfession().getId())
                .build();
    }

    public String getPriceUa(BigDecimal price){
        return String.format("%.2f грн",price.multiply(new BigDecimal(26)).
                round(new MathContext(0, RoundingMode.HALF_UP))) ;
    }

    public String getPriceEn(BigDecimal price){
        return String.format("%.0f $",price) ;
    }
}
