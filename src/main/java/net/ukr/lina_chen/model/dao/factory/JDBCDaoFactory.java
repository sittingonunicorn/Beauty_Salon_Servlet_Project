package net.ukr.lina_chen.model.dao.factory;


import net.ukr.lina_chen.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public MasterDao createMasterDao() {
        return new JDBCMasterDao(getConnection());
    }

    @Override
    public ProfessionDao createProfessionDao() {
        return new JDBCProfessionDao(getConnection());
    }

    @Override
    public BeautyserviceDao createBeautyserviceDao() {
        return new JDBCBeautyserviceDao(getConnection());
    }

    @Override
    public AppointmentDao createAppointmentDao(){
        return new JDBCAppointmentDao(getConnection());
    }

    @Override
    public ArchiveDao createArchiveDao() {
        return new JDBCArchiveDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
