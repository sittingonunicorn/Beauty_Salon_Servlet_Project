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
    private final Locale locale;

    public JDBCProfessionDao(Connection connection, Locale locale) {
        this.connection = connection;
        this.locale = locale;
    }

    @Override
    public Profession findById(Long id) {
        Profession profession = new Profession();
        Map<Long, Master> masters = new HashMap<>();
        try (PreparedStatement st = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.profession.by.id"), locale))) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    profession = professionMapper.extractFromResultSet(rs, locale);
                    Master master = masterMapper.extractFromResultSet(rs, locale);
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
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.all.professions"), locale));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Profession profession = professionMapper.extractFromResultSet(rs, locale);
                profession = professionMapper.makeUnique(professions, profession);
                Master master = masterMapper.extractFromResultSet(rs, locale);
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

    @Override
    public List<Profession> findAllServicetypes() {
        Map<Long, Profession> professions = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.all.professions"), locale));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Profession profession = professionMapper.extractFromResultSet(rs, locale);
                professionMapper.makeUnique(professions, profession);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(professions.values());
    }
}
