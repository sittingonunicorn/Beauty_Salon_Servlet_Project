package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.*;

import java.util.Locale;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao(Locale locale);
    public abstract MasterDao createMasterDao(Locale locale);
    public abstract ProfessionDao createProfessionDao(Locale locale);
    public abstract BeautyserviceDao createBeautyserviceDao(Locale locale);
    public abstract AppointmentDao createAppointmentDao(Locale locale);
    public abstract ArchiveDao createArchiveDao(Locale locale);
    public abstract TransactionDao createTransactionDao(Locale locale);

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
