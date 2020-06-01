package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.exceptions.UserExistsException;
import net.ukr.lina_chen.model.dao.UserDao;
import net.ukr.lina_chen.model.dao.mapper.RolesMapper;
import net.ukr.lina_chen.model.dao.mapper.UserMapper;
import net.ukr.lina_chen.model.entity.Role;
import net.ukr.lina_chen.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;
    private final UserMapper mapper = new UserMapper();
    private final RolesMapper rolesMapper = new RolesMapper();
    private final ResourceBundle bundle;

    public JDBCUserDao(Connection connection, ResourceBundle bundle) {
        this.connection = connection;
        this.bundle = bundle;
    }

    public Long create(User entity) throws UserExistsException {
        long userId = 0L;
        try (PreparedStatement checkIfExists = connection.prepareStatement(bundle.getString("query.find.user.by.email"));
             PreparedStatement replaceUser = connection.prepareStatement(bundle.getString("query.replace.user"));
             PreparedStatement getId = connection.prepareStatement(bundle.getString("query.get.user.id"));
             PreparedStatement setRoles = connection.prepareStatement(bundle.getString("query.replace.user.role"))) {
            replaceUser.setString(1, entity.getEmail());
            replaceUser.setString(2, entity.getName());
            replaceUser.setString(3, entity.getNameUkr());
            replaceUser.setString(4, entity.getPassword());
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
            setRoles.setLong(1, userId);
            setRoles.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        return userId;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        Map<Long, User> users = new HashMap<>();
        Set<Role> roles = new HashSet<>();
        try (PreparedStatement st = connection.prepareStatement(bundle.getString("query.find.user.by.id"))) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    user = mapper.extractFromResultSet(rs);
                    user = mapper.makeUnique(users, user);
                    roles.add(rolesMapper.extractFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        if (user != null) {
            user.setRoles(roles);
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
        Map<Long, User> users = new HashMap<>();
        Set<Role> roles = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(bundle.getString("query.find.user.by.email"))) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = Optional.ofNullable(mapper.extractFromResultSet(rs));
                    user.ifPresent(value -> mapper.makeUnique(users, value));
                    roles.add(rolesMapper.extractFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        user.ifPresent(value -> value.setRoles(roles));
        return user;
    }

}

