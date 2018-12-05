package ua.com.training.model.dao.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import ua.com.training.model.services.ResourceManager;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPool {
    private volatile static DataSource dataSource;


    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource basicDataSource = new BasicDataSource();
                    ResourceBundle dbUtilBundle = ResourceManager.getBundle(ResourceManager.DB_UTILS_BUNDLE_NAME);
                    basicDataSource.setUrl(ResourceManager.getProperty(dbUtilBundle, "url"));
                    basicDataSource.setUsername(ResourceManager.getProperty(dbUtilBundle, "user"));
                    basicDataSource.setPassword(ResourceManager.getProperty(dbUtilBundle, "password"));
                    basicDataSource.setMinIdle(5);
                    basicDataSource.setMaxIdle(10);
                    basicDataSource.setMaxOpenPreparedStatements(100);
                }
            }

        }
        return dataSource;
    }
}
