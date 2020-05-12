package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.ProfessionDao;
import net.ukr.lina_chen.model.dao.mapper.MasterMapper;
import net.ukr.lina_chen.model.dao.mapper.ProfessionMapper;
import net.ukr.lina_chen.model.dao.mapper.UserMapper;
import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.entity.Profession;
import net.ukr.lina_chen.model.entity.User;
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
    private final UserMapper userMapper = new UserMapper();
    private final ProfessionMapper professionMapper = new ProfessionMapper();

    public JDBCProfessionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Profession entity) throws SQLException {

    }

    @Override
    public Profession findById(int id) {
        return null;
    }

    @Override
    public List<Profession> findAll() {
        Map<Long, Profession> professions = new HashMap<>();
        try(PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                Profession profession = professionMapper.extractFromResultSet(rs);
                profession = professionMapper.makeUnique(professions, profession);
                Master master = masterMapper.extractFromResultSet(rs);
                User user = userMapper.extractFromResultSet(rs);
                master.setUser(user);
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
    public void delete(int id) {

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
