package net.ukr.lina_chen.model.dao.factory;

import net.ukr.lina_chen.model.dao.DAOUtility;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    private ConnectionPoolHolder() {
    }

    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    DAOUtility daoUtility = new DAOUtility(ds);
                    daoUtility.setConnectionData();
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }


}
