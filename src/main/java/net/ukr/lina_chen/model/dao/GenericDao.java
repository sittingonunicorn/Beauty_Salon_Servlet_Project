package net.ukr.lina_chen.model.dao;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public interface GenericDao<T> extends AutoCloseable {
    ResourceBundle queryBundle = ResourceBundle.getBundle("queries");
    T findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(Long id);
    void close();
    default String getLocalizedQuery(String query, Locale locale){
        return query.replaceAll("_lang", "_"+locale.getLanguage());
    }
}
