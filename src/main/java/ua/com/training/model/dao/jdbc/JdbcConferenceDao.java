package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.mappers.ConferenceMapper;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;
import ua.com.training.model.services.ResourceService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcConferenceDao implements ConferenceDao {
    private final static Logger LOG = LogManager.getLogger(JdbcConferenceDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle = new ResourceService()
            .getBundle(ResourceService.SQL_REQUESTS_BUNDLE_NAME);


    @Override
    public Conference getById(long id) {
        return null;
    }

    @Override
    public List<Conference> getAll() {
        List<Conference> conferences = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("conferences.select.all."))) {

            ResultSet resultSet = conferenceStatement.executeQuery();
            conferences = new ConferenceMapper().mapToList(resultSet);

        } catch (SQLException e) {
            LOG.error("Cant get all conferences: " + e);
        }
        return conferences;

    }

    @Override
    public void update(Conference item) {

    }

    @Override
    public boolean delete(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement query = connection
                     .prepareStatement(sqlRequestBundle.getString("conference.delete"))) {
            query.setLong(1, id);
            query.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("Delete conference failed: " + e);
            return false;
        }
    }

    @Override
    public boolean addNew(Conference conference) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceQuery = connection
                     .prepareStatement(sqlRequestBundle.getString("conference.insert"), Statement.RETURN_GENERATED_KEYS);
             PreparedStatement reportQuery = connection
                     .prepareStatement((sqlRequestBundle.getString("report.insert")))) {
            conferenceQuery.setString(1, conference.getTopic());
            conferenceQuery.setString(2, conference.getLocation());
            conferenceQuery.setTimestamp(3, Timestamp.valueOf(conference.getDateTime()));
            conferenceQuery.executeUpdate();
            ResultSet idResultSet = conferenceQuery.getGeneratedKeys();
            if (idResultSet.next()) {

                for (Report report : conference.getReports()) {
                    reportQuery.setString(1, report.getTopic());
                    reportQuery.setLong(2, idResultSet.getLong(1));
                    reportQuery.setLong(3, report.getSpeakerId());
                    reportQuery.setTimestamp(4,Timestamp.valueOf(report.getDateTime()));
                    reportQuery.addBatch();
                }
            }
            reportQuery.executeBatch();
            return true;
        } catch (SQLException e) {
            LOG.error("Conference inserting failed: " + e);
        }
        return false;
    }
}
