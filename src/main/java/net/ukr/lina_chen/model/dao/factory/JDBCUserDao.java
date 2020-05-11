package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.UserDao;
import net.ukr.lina_chen.model.dao.mapper.UserMapper;
import net.ukr.lina_chen.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) throws SQLException {
        final String query = "replace into users (email, name, name_ukr, password, role) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getNameUkr());
            ps.setString(4, entity.getPassword());
            ps.setString(5, entity.getRole().name());
            ps.executeUpdate();
                } catch (SQLException e) {
            logger.error(e);
            throw new SQLException();
        }
    }

    @Override
    public User findById(int id) {


        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<User> user = Optional.empty();
        final String query = "select * from users where email='" + email + "'";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            UserMapper mapper = new UserMapper();
            if (rs.next()) {
                user = Optional.of(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

}

