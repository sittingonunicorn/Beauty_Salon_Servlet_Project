package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.exceptions.UserExistsException;
import net.ukr.lina_chen.model.dao.UserDao;
import net.ukr.lina_chen.model.dao.mapper.UserMapper;
import net.ukr.lina_chen.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;
    private final UserMapper mapper = new UserMapper();
    private final Locale locale;

    public JDBCUserDao(Connection connection, Locale locale) {
        this.connection = connection;
        this.locale = locale;
    }

    public Long create(User entity) throws UserExistsException, SQLException {
        long userId = 0L;
        try (PreparedStatement checkIfExists = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.user.by.email"), locale));
             PreparedStatement replaceUser = connection.prepareStatement(
                     getLocalizedQuery(queryBundle.getString("query.replace.user"), locale));
             PreparedStatement getId = connection.prepareStatement(
                     getLocalizedQuery(queryBundle.getString("query.get.user.id"), locale));
             PreparedStatement updateSequence = connection.prepareStatement(
                     getLocalizedQuery(queryBundle.getString("query.update.sequence"), locale))) {
            replaceUser.setString(1, entity.getEmail());
            replaceUser.setString(2, entity.getName());
            replaceUser.setString(3, entity.getNameUkr());
            replaceUser.setString(4, entity.getPassword());
            checkIfExists.setString(1, entity.getEmail());
            connection.setAutoCommit(false);
            try (ResultSet rs = checkIfExists.executeQuery()) {
                if (rs.next()) {
                    throw new UserExistsException();
                }
            }
            replaceUser.executeUpdate();
            try (ResultSet resultSet = getId.executeQuery()) {
                while (resultSet.next()) {
                    userId = resultSet.getLong("user_id");
                }
            }
            updateSequence.setLong(1, userId);
            updateSequence.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                throw new SQLException();
            }
        }
        return userId;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try (PreparedStatement st = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.user.by.id"), locale))) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    user = mapper.extractFromResultSet(rs, locale);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User entity) {
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
    public Optional<User> findUserByEmail(String email) {
        Optional<User> user = Optional.empty();
        Map<Long, User> users = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.user.by.email"), locale))) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = Optional.ofNullable(mapper.extractFromResultSet(rs, locale));
                    user.ifPresent(value -> mapper.makeUnique(users, value));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

}

