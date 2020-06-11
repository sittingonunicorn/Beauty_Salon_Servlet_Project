package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.ServiceTypeDao;
import net.ukr.lina_chen.model.dao.mapper.MasterMapper;
import net.ukr.lina_chen.model.dao.mapper.ServiceTypeMapper;
import net.ukr.lina_chen.model.entity.Master;
import net.ukr.lina_chen.model.entity.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCServiceTypeDao implements ServiceTypeDao {
    private static final Logger logger = LogManager.getLogger(JDBCServiceTypeDao.class);
    private final Connection connection;
    private final MasterMapper masterMapper = new MasterMapper();
    private final ServiceTypeMapper serviceTypeMapper = new ServiceTypeMapper();
    private final Locale locale;

    public JDBCServiceTypeDao(Connection connection, Locale locale) {
        this.connection = connection;
        this.locale = locale;
    }

    @Override
    public ServiceType findById(Long id) {
        ServiceType serviceType = new ServiceType();
        Map<Long, Master> masters = new HashMap<>();
        try (PreparedStatement st = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.serviceType.by.id"), locale))) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    serviceType = serviceTypeMapper.extractFromResultSet(rs, locale);
                    Master master = masterMapper.extractFromResultSet(rs, locale);
                    masterMapper.makeUnique(masters, master);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        serviceType.getMasters().addAll(masters.values());
        return serviceType;
    }


    @Override
    public List<ServiceType> findAll() {
        Map<Long, ServiceType> professions = new HashMap<>();
        Map<Long, Master> masters = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.all.serviceTypes"), locale));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ServiceType serviceType = serviceTypeMapper.extractFromResultSet(rs, locale);
                serviceType = serviceTypeMapper.makeUnique(professions, serviceType);
                Master master = masterMapper.extractFromResultSet(rs, locale);
                serviceType.getMasters().add(masterMapper.makeUnique(masters, master));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(professions.values());
    }

    @Override
    public void update(ServiceType entity) {
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
    public List<ServiceType> findAllServicetypes() {
        Map<Long, ServiceType> professions = new HashMap<>();
        Map<Long, Master> masters = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(
                getLocalizedQuery(queryBundle.getString("query.find.all.serviceTypes"), locale));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ServiceType serviceType = serviceTypeMapper.extractFromResultSet(rs, locale);
                serviceTypeMapper.makeUnique(professions, serviceType);
                Master master = masterMapper.extractFromResultSet(rs, locale);
                serviceType.getMasters().add(masterMapper.makeUnique(masters, master));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return new ArrayList<>(professions.values());
    }
}
