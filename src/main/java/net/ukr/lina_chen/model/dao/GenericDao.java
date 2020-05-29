package net.ukr.lina_chen.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    T findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(Long id);
    void close();
}
