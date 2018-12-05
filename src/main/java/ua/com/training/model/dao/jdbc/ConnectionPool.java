package ua.com.training.model.dao.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import ua.com.training.model.services.Resources;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPool {
    private  volatile static  DataSource dataSource;


    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class){
                if (dataSource == null){
                    BasicDataSource basicDataSource = new BasicDataSource();
                    ResourceBundle dbResourceBundle = Resources.DB_UTILS.getBundle();
                    basicDataSource.setUrl(dbResourceBundle.getString("url"));
                    basicDataSource.setUsername(dbResourceBundle.getString("user"));
                    basicDataSource.setPassword(dbResourceBundle.getString("password"));
                    basicDataSource.setMinIdle(5);
                    basicDataSource.setMaxIdle(10);
                    basicDataSource.setMaxOpenPreparedStatements(100);
                }
            }

        }
        return dataSource;
    }
}
