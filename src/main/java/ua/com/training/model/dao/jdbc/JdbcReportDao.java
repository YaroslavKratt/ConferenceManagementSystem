package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.ReportDao;
import ua.com.training.model.dao.mappers.Mapper;
import ua.com.training.model.dao.mappers.ReportMapper;
import ua.com.training.model.entity.Report;
import ua.com.training.model.ResourceEnum;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcReportDao implements ReportDao {
    private static final Logger LOG = LogManager.getLogger(JdbcReportDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle =ResourceBundle
            .getBundle(ResourceEnum.SQL_REQUESTS_BUNDLE.getBundleName());
    private Mapper<Report> reportMapper = new ReportMapper();

    @Override
    public Report getById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.select.by.id"))) {

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
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Report item) {

    }

    @Override
    public boolean delete(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.delete"))) {

            preparedStatement.setLong(1, id);

            return preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("Removing of report has failed:  " + e);
        }
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

    @Override
    public void addNew(long conferenceId, Report report) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.add.new.to.conference"))) {
            preparedStatement.setString(1, report.getTopic());
            preparedStatement.setLong(2, conferenceId);
            preparedStatement.setLong(3,report.getSpeakerId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(report.getDateTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("Add new report to conference failed: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public int getAmountOfSubscribedUsers(long reportId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.get.amount.of.subscribed.users"))) {

            preparedStatement.setLong(1, reportId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOG.error("Get amount of subscribed users failed: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void setAmountOfSubscribedUsers(long reportId, int amount) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.set.amount.of.subscribed.users"))) {
            preparedStatement.setInt(1,amount);
            preparedStatement.setLong(2, reportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can`t set  amount of subscribed users : " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void setComersAmount(long reportId, int amount) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.set.amount.of.incoming.users"))) {
            preparedStatement.setInt(1,amount);
            preparedStatement.setLong(2, reportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can`t set  amount of incoming users : " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public int getComersAmount(long reportId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("report.get.amount.of.incoming.users"))) {

            preparedStatement.setLong(1, reportId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOG.error("Get amount of subscribed users failed: " + e);
            throw new RuntimeException();
        }

    }
}
