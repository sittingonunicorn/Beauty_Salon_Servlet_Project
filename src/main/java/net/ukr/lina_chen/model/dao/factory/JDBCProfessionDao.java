package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.ProfessionDao;
import net.ukr.lina_chen.model.dao.mapper.MasterMapper;
import net.ukr.lina_chen.model.dao.mapper.ProfessionMapper;
import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.entity.Profession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCProfessionDao implements ProfessionDao {
    private static final Logger logger = LogManager.getLogger(JDBCProfessionDao.class);
    private final Connection connection;
    private final MasterMapper masterMapper = new MasterMapper();
    private final ProfessionMapper professionMapper = new ProfessionMapper();

    public JDBCProfessionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Profession findById(Long id) {
        Profession profession = null;
        Map<Long, Master> masters = new HashMap<>();
        try (PreparedStatement st = connection.prepareStatement(QUERY_FIND_BY_ID)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    profession = professionMapper.extractFromResultSet(rs);
                    Master master = masterMapper.extractFromResultSet(rs);
                    master = masterMapper.makeUnique(masters, master);
                    master.setProfession(profession);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        profession.getMasters().addAll(masters.values());
        return profession;
    }


    @Override
    public List<Profession> findAll() {
        Map<Long, Profession> professions = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Profession profession = professionMapper.extractFromResultSet(rs);
                profession = professionMapper.makeUnique(professions, profession);
                Master master = masterMapper.extractFromResultSet(rs);
                master.setProfession(profession);
                profession.getMasters().add(master);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(professions.values());
    }

    @Override
    public void update(Profession entity) {

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
    public List<Profession> findAllServicetypes() {
        Map<Long, Profession> professions = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Profession profession = professionMapper.extractFromResultSet(rs);
                professionMapper.makeUnique(professions, profession);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(professions.values());
    }
}
