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

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    public Long create(User entity) throws UserExistsException {
        long userId = 0L;
        try (PreparedStatement checkIfExists = connection.prepareStatement(QUERY_FIND_BY_EMAIL);
                PreparedStatement replaceUser = connection.prepareStatement(QUERY_REPLACE_USER);
             PreparedStatement getId = connection.prepareStatement(QUERY_GET_ID);
             PreparedStatement setRoles = connection.prepareStatement(QUERY_REPLACE_USER_ROLE)) {
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
        try (PreparedStatement st = connection.prepareStatement(QUERY_FIND_BY_ID)) {
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
        Optional<User>  user = Optional.empty();
        Map<Long, User> users = new HashMap<>();
        Set<Role> roles = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = Optional.ofNullable(mapper.extractFromResultSet(rs));
                    user.ifPresent(value -> mapper.makeUnique(users,value));
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

