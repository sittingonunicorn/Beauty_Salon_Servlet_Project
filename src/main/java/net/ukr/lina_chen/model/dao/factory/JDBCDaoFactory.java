package net.ukr.lina_chen.model.dao.factory;


import net.ukr.lina_chen.model.dao.MasterDao;
import net.ukr.lina_chen.model.dao.ProfessionDao;
import net.ukr.lina_chen.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

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

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
