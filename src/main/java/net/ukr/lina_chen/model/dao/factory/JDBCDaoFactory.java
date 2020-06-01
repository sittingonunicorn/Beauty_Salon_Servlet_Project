package net.ukr.lina_chen.model.dao.factory;


import net.ukr.lina_chen.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao(ResourceBundle bundle) {
        return new JDBCUserDao(getConnection(), bundle);
    }

    @Override
    public MasterDao createMasterDao(ResourceBundle bundle) {
        return new JDBCMasterDao(getConnection(), bundle);
    }

    @Override
    public ProfessionDao createProfessionDao(ResourceBundle bundle) {
        return new JDBCProfessionDao(getConnection(), bundle);
    }

    @Override
    public BeautyserviceDao createBeautyserviceDao(ResourceBundle bundle) {
        return new JDBCBeautyserviceDao(getConnection(), bundle);
    }

    @Override
    public AppointmentDao createAppointmentDao(ResourceBundle bundle){
        return new JDBCAppointmentDao(getConnection(), bundle);
    }

    @Override
    public ArchiveDao createArchiveDao(ResourceBundle bundle) {
        return new JDBCArchiveDao(getConnection(), bundle);
    }

    @Override
    public TransactionDao createTransactionDao(ResourceBundle bundle) {
        return new JDBCTransactionDao(getConnection(), bundle);
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
