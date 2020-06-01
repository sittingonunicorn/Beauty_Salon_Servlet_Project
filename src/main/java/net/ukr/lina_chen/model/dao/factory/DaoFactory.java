package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.*;

import java.util.ResourceBundle;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao(ResourceBundle bundle);
    public abstract MasterDao createMasterDao(ResourceBundle bundle);
    public abstract ProfessionDao createProfessionDao(ResourceBundle bundle);
    public abstract BeautyserviceDao createBeautyserviceDao(ResourceBundle bundle);
    public abstract AppointmentDao createAppointmentDao(ResourceBundle bundle);
    public abstract ArchiveDao createArchiveDao(ResourceBundle bundle);
    public abstract TransactionDao createTransactionDao(ResourceBundle bundle);

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
