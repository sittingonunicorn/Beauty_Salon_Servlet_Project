package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.mapper.MasterMapper;
import net.ukr.lina_chen.model.entity.Master;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCMasterDao implements MasterDao {
    private static final Logger logger = LogManager.getLogger(JDBCMasterDao.class);
    private final Connection connection;
    private final MasterMapper masterMapper = new MasterMapper();
    private final ResourceBundle bundle;

    public JDBCMasterDao(Connection connection, ResourceBundle bundle) {
        this.connection = connection;
        this.bundle = bundle;
    }

    @Override
    public Master findById(Long id) {
        Master master = null;
        try (PreparedStatement st = connection.prepareStatement(bundle.getString("query.find.master.by.id"))) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    master = masterMapper.extractFromResultSet(rs);
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
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.all.masters"));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Master master = masterMapper.extractFromResultSet(rs);
                resultList.add(master);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return resultList;
    }

    @Override
    public void update(Master entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Master findByUserId(Long userId) {
        Master master = null;
        try (PreparedStatement st = connection.prepareStatement(bundle.getString("query.find.master.by.user.id"))) {
            st.setLong(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    master = masterMapper.extractFromResultSet(rs);
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
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.master.by.profession.id"))) {
            ps.setLong(1, professionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Master master = masterMapper.extractFromResultSet(rs);
                    resultList.add(master);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return resultList;
    }


}
