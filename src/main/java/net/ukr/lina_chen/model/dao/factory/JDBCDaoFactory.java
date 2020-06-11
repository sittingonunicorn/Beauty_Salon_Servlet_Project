package net.ukr.lina_chen.model.dao.factory;


import net.ukr.lina_chen.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao(Locale locale) {
        return new JDBCUserDao(getConnection(), locale);
    }

    @Override
    public MasterDao createMasterDao(Locale locale) {
        return new JDBCMasterDao(getConnection(), locale);
    }

    @Override
    public ServiceTypeDao createServiceTypeDao(Locale locale) {
        return new JDBCServiceTypeDao(getConnection(), locale);
    }

    @Override
    public BeautyserviceDao createBeautyserviceDao(Locale locale) {
        return new JDBCBeautyserviceDao(getConnection(), locale);
    }

    @Override
    public AppointmentDao createAppointmentDao(Locale locale){
        return new JDBCAppointmentDao(getConnection(), locale);
    }

    @Override
    public ArchiveDao createArchiveDao(Locale locale) {
        return new JDBCArchiveDao(getConnection(), locale);
    }

    @Override
    public TransactionDao createTransactionDao(Locale locale) {
        return new JDBCTransactionDao(getConnection(), locale);
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
