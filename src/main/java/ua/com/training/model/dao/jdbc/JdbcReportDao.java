package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.ReportDao;
import ua.com.training.model.dao.mappers.Mapper;
import ua.com.training.model.dao.mappers.ReportMapper;
import ua.com.training.model.entity.Report;
import ua.com.training.model.services.ResourceService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcReportDao implements ReportDao {
    private static final Logger LOG = LogManager.getLogger(JdbcReportDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle = new ResourceService()
            .getBundle(ResourceService.SQL_REQUESTS_BUNDLE_NAME);
    private Mapper<Report> reportMapper = new ReportMapper();

    @Override
    public Report getById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("report.select.by.id"))) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return reportMapper.mapToObject(resultSet);
        } catch (SQLException e) {
            LOG.error("getById failed: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Report> getAll() {
        return null;
    }

    @Override
    public void update(Report item) {

    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean addNew(Report item) {
        return false;
    }

    @Override
    public boolean subscribe(long userId, long reportId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.subscribe.user"))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, reportId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("SubscriptionDTO failed:  " + e);
        }

        return false;
    }

    @Override
    public boolean checkSubscription(long userId, long reportId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.select.subscription.by.id"))) {

            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, reportId);
            return preparedStatement.executeQuery().next();

        } catch (SQLException e) {
            LOG.error("Check subscription failed: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void unsubscribe(long userId, long reportId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.unsubscribe.user"))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, reportId);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("Unsubscription failed: " + e);
            throw new RuntimeException();
        }
    }
}
