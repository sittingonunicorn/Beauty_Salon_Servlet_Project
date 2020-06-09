package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.BeautyserviceDao;
import net.ukr.lina_chen.model.dao.mapper.BeautyserviceMapper;
import net.ukr.lina_chen.model.entity.BeautyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JDBCBeautyserviceDao implements BeautyserviceDao {
    private static final Logger logger = LogManager.getLogger(JDBCBeautyserviceDao.class);
    private final Connection connection;
    private final BeautyserviceMapper beautyserviceMapper = new BeautyserviceMapper();
    private final Locale locale;

    public JDBCBeautyserviceDao(Connection connection, Locale locale) {
        this.connection = connection;
        this.locale = locale;
    }

    @Override
    public List<BeautyService> findByProfessionId(Long professionId) {
        List<BeautyService> resultList = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.beautyservice.by.profession.id"), locale))) {
            st.setLong(1, professionId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    BeautyService beautyService = beautyserviceMapper.extractFromResultSet(rs, locale);
                    resultList.add(beautyService);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return resultList;
    }

    @Override
    public BeautyService findById(Long id) {
        BeautyService beautyService = null;
        try (PreparedStatement st = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.beautyservice.by.id"), locale))) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    beautyService = beautyserviceMapper.extractFromResultSet(rs, locale);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return beautyService;
    }

    @Override
    public List<BeautyService> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(BeautyService entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
