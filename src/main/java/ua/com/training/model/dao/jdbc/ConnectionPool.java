package ua.com.training.model.dao.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import ua.com.training.model.services.ResourceService;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPool {
    private volatile static DataSource dataSource;


    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource basicDataSource = new BasicDataSource();
                    ResourceBundle dbUtilBundle = new ResourceService().getBundle(ResourceService.DB_UTILS_BUNDLE_NAME);
                    basicDataSource.setDriverClassName(dbUtilBundle.getString("driver"));
                    basicDataSource.setUrl(dbUtilBundle.getString("url"));
                    basicDataSource.setUsername(dbUtilBundle.getString("user"));
                    basicDataSource.setPassword(dbUtilBundle.getString("password"));
                    basicDataSource.setMinIdle(5);
                    basicDataSource.setMaxIdle(10);
                    basicDataSource.setMaxOpenPreparedStatements(100);
                    dataSource = basicDataSource;
                }
            }

        }
        return dataSource;
    }
}
