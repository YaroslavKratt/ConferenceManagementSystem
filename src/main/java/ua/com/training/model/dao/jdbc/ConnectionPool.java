package ua.com.training.model.dao.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import ua.com.training.model.ResourceEnum;

import javax.sql.DataSource;
import java.util.ResourceBundle;

class ConnectionPool {
    private volatile static DataSource dataSource;


    static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource basicDataSource = new BasicDataSource();
                    ResourceBundle dbUtilBundle = ResourceBundle.getBundle(ResourceEnum.ACCESS_BUNDLE.getBundleName());
                    basicDataSource.setDriverClassName(dbUtilBundle.getString("mysql.driver"));
                    basicDataSource.setUrl(dbUtilBundle.getString("mysql.url"));
                    basicDataSource.setUsername(dbUtilBundle.getString("mysql.user"));
                    basicDataSource.setPassword(dbUtilBundle.getString("mysql.password"));
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
