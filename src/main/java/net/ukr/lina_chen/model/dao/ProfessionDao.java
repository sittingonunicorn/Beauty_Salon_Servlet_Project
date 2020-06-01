package net.ukr.lina_chen.model.dao;

import net.ukr.lina_chen.model.entity.Profession;

import java.util.List;

public interface ProfessionDao extends GenericDao<Profession> {
    List<Profession> findAllServicetypes();
}
