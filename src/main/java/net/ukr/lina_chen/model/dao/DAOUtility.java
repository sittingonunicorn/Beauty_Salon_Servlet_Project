package net.ukr.lina_chen.model.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static net.ukr.lina_chen.model.dao.IDatabaseConstants.*;

public class DAOUtility {
    private static final Logger logger = LogManager.getLogger(DAOUtility.class);

    private final BasicDataSource ds;

    public DAOUtility(BasicDataSource ds) {
        this.ds = ds;
    }

    public void setConnectionData() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(PROPERTIES)){
            Properties property = new Properties();
            property.load(is);
            ds.setUrl(property.getProperty(DATABASE_URL));
            ds.setUsername(property.getProperty(DATABASE_USER));
            ds.setPassword(property.getProperty(DATABASE_PASSWORD));
            ds.setMinIdle(Integer.parseInt(property.getProperty(DATABASE_MIN_IDLE)));
            ds.setMaxIdle(Integer.parseInt(property.getProperty(DATABASE_MAX_IDLE)));
            ds.setMaxOpenPreparedStatements(
                    Integer.parseInt(property.getProperty(DATABASE_MAX_OPEN_PREPARED_STATEMENTS)));
        }catch (IOException e) {
            logger.error("Properties file error");
        }
    }
}
