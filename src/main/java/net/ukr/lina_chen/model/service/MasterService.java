package net.ukr.lina_chen.model.service;

import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.factory.DaoFactory;
import net.ukr.lina_chen.model.entity.Master;

import java.util.Optional;

public class MasterService {
    private final DaoFactory factory = DaoFactory.getInstance();

    public Optional<Master> getById(Long masterId) {
        Optional<Master> master;
    try(MasterDao masterDao = factory.createMasterDao()){
        master = Optional.ofNullable(masterDao.findById(masterId));
    }
    return master;
    }

    public Optional<Master> getByUserId(Long userId) {
        Optional<Master> master;
        try(MasterDao masterDao = factory.createMasterDao()){
            master = Optional.ofNullable(masterDao.findByUserId(userId));
        }
        return master;
    }
}
