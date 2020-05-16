package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.mapper.BeautyserviceMapper;
import net.ukr.lina_chen.model.dao.mapper.MasterMapper;
import net.ukr.lina_chen.model.dao.mapper.ProfessionMapper;
import net.ukr.lina_chen.model.dao.mapper.UserMapper;
import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCMasterDao implements MasterDao {
    private static final Logger logger = LogManager.getLogger(JDBCMasterDao.class);
    private final Connection connection;
    private final MasterMapper masterMapper = new MasterMapper();
    private final UserMapper userMapper = new UserMapper();

    public JDBCMasterDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Master entity) throws SQLException {

    }

    @Override
    public Master findById(Long id) {
        Master master = null;
        try (PreparedStatement st = connection.prepareStatement(SQL_FIND_BY_ID)) {
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
        try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL);
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
}
