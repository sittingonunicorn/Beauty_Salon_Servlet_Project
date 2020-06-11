package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.dto.MasterDTO;
import net.ukr.lina_chen.model.entity.Master;

import java.util.*;
import java.util.stream.Collectors;

public class MasterService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public Optional<Master> getById(Long masterId, Locale locale) {
        Optional<Master> master;
        try (MasterDao masterDao = factory.createMasterDao(locale)) {
            master = Optional.ofNullable(masterDao.findById(masterId));
        }
        return master;
    }

    public Optional<MasterDTO> getByUserId(Long userId, Locale locale) {
        Master master;
        try (MasterDao masterDao = factory.createMasterDao(locale)) {
            master = masterDao.findByUserId(userId);
        }
        return Optional.ofNullable(getDTO(master));
    }

    public List<MasterDTO> findAllOrderByNameAsc(Locale locale) {
        List<Master> masters;
        try (MasterDao masterDao = factory.createMasterDao(locale)) {
            masters = masterDao.findAll();
        }
        return masters.stream().map(this::getDTO)
                .collect(Collectors.toList());
    }

    public List<MasterDTO> findByProfessionIdOrderByNameAsc(Long professionId, Locale locale) {
        List<Master> masters;
        try (MasterDao masterDao = factory.createMasterDao(locale)) {
            masters = masterDao.findByServiceTypeId(professionId);
        }
        return masters.stream().map(this::getDTO)
                .collect(Collectors.toList());
    }

    private MasterDTO getDTO(Master master) {
        return MasterDTO.MasterDTOBuilder.masterDTO()
                .withId(master.getId())
                .withName(master.getUser().getName())
                .withTimeBegin(master.getTimeBegin())
                .withTimeEnd(master.getTimeEnd())
                .build();
    }
}
