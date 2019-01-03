package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {
    private static final Logger logger =  LogManager.getLogger(DaoFactory.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao();
    }

    @Override
    public ReportDao createReportDao() {
        return new JdbcReportDao();
    }

    @Override
    public SpeakerDao createSpeakerDao() {
        return new JdbcSpeakerDao();
    }

    @Override
    public ConferenceDao createConferenceDao() {
        return new JdbcConferenceDao();
    }

}
