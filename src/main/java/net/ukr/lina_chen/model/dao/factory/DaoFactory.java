package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.*;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract MasterDao createMasterDao();
    public abstract ProfessionDao createProfessionDao();
    public abstract BeautyserviceDao createBeautyserviceDao();
    public abstract AppointmentDao createAppointmentDao();
    public abstract ArchiveDao createArchiveDao();
    public abstract TransactionDao createTransactionDao();

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
