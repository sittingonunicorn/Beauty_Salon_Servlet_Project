package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.MasterDTO;
import net.ukr.lina_chen.model.entity.Master;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MasterService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public Optional<Master> getById(Long masterId) {
        Optional<Master> master;
        try (MasterDao masterDao = factory.createMasterDao()) {
            master = Optional.ofNullable(masterDao.findById(masterId));
        }
        return master;
    }

    public Optional<MasterDTO> getByUserId(Long userId, boolean isLocaleEn) {
        Master master;
        try (MasterDao masterDao = factory.createMasterDao()) {
            master = masterDao.findByUserId(userId);
        }
        return Optional.ofNullable(getLocalizedDTO(master, isLocaleEn));
    }

    public List<MasterDTO> findAllOrderByNameAsc(boolean isLocaleEn) {
        List<Master> masters;
        try (MasterDao masterDao = factory.createMasterDao()) {
            masters = masterDao.findAll();
        }
        return masters.stream().map(m -> getLocalizedDTO(m, isLocaleEn))
                .sorted(Comparator.comparing(MasterDTO::getName))
                .collect(Collectors.toList());
    }

    public List<MasterDTO> findByProfessionIdOrderByNameAsc(Long professionId, boolean isLocaleEn) {
        List<Master> masters;
        try (MasterDao masterDao = factory.createMasterDao()) {
            masters = masterDao.findByProfessionId(professionId);
        }
        return masters.stream().map(m -> getLocalizedDTO(m, isLocaleEn))
                .sorted(Comparator.comparing(MasterDTO::getName))
                .collect(Collectors.toList());
    }

    private MasterDTO getLocalizedDTO(Master master, boolean isLocaleEn) {
        return MasterDTO.MasterDTOBuilder.masterDTO()
                .withId(master.getId())
                .withName(isLocaleEn ? master.getUser().getName() : master.getUser().getNameUkr())
                .withTimeBegin(master.getTimeBegin())
                .withTimeEnd(master.getTimeEnd())
                .build();
    }
}
