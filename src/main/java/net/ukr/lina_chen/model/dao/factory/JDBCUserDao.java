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
    private final UserMapper mapper = new UserMapper();

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_REPLACE)) {
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
    public User findById(Long id) {
        User user = null;
        try (PreparedStatement st = connection.prepareStatement(SQL_FIND_BY_ID)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    user = mapper.extractFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
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
    public Optional<User> findUserByEmail(String email) {
        Optional<User> user = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = Optional.of(mapper.extractFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

}

