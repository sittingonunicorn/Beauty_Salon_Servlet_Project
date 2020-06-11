package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.ServiceType;

import java.util.List;

public interface ServiceTypeDao extends GenericDao<ServiceType> {
    List<ServiceType> findAllServicetypes();
}
