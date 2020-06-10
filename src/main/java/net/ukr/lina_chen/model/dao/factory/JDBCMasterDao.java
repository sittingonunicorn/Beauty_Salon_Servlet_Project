package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.mapper.MasterMapper;
import net.ukr.lina_chen.model.entity.Master;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JDBCMasterDao implements MasterDao {
    private static final Logger logger = LogManager.getLogger(JDBCMasterDao.class);
    private final Connection connection;
    private final MasterMapper masterMapper = new MasterMapper();
    private final Locale locale;

    public JDBCMasterDao(Connection connection, Locale locale) {
        this.connection = connection;
        this.locale = locale;
    }

    @Override
    public Master findById(Long id) {
        Master master = null;
        try (PreparedStatement st = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.master.by.id"), locale))) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    master = masterMapper.extractFromResultSet(rs, locale);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return master;
    }


    @Override
    public List<Master> findAll() {
        List<Master> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.all.masters"), locale));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Master master = masterMapper.extractFromResultSet(rs, locale);
                resultList.add(master);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return resultList;
    }

    @Override
    public void update(Master entity) {
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
            logger.error(e.getMessage());
        }
    }

    @Override
    public Master findByUserId(Long userId) {
        Master master = null;
        try (PreparedStatement st = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.master.by.user.id"), locale))) {
            st.setLong(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    master = masterMapper.extractFromResultSet(rs, locale);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return master;
    }

    @Override
    public List<Master> findByProfessionId(Long professionId) {
        List<Master> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.master.by.profession.id"), locale))) {
            ps.setLong(1, professionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Master master = masterMapper.extractFromResultSet(rs, locale);
                    resultList.add(master);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return resultList;
    }

}
