package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.ResourceEnum;
import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.mappers.ConferenceMapper;
import ua.com.training.model.dao.mappers.SubscriptionDtoMapper;
import ua.com.training.model.dto.SubscriptionDTO;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcConferenceDao implements ConferenceDao {
    private final static Logger LOG = LogManager.getLogger(JdbcConferenceDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle = ResourceBundle.getBundle(ResourceEnum.SQL_REQUESTS_BUNDLE.getBundleName());


    @Override
    public Conference getById(long id,String language) {
        Conference conference;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("conference.select.by.id"))) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            conference = new ConferenceMapper().mapToObject(resultSet, language);
            return conference;

        } catch (SQLException e) {
            LOG.error("Cant get conference  by id:" + e);
            throw new RuntimeException();
        }
    }


    @Override
    public List<Conference> getAll(String language) {
        List<Conference> conferences = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("conferences.select.all."))) {

            ResultSet resultSet = conferenceStatement.executeQuery();
            conferences = new ConferenceMapper().mapToList(resultSet, language);

        } catch (SQLException e) {
            LOG.error("Cant get all conferences: " + e);
        }
        return conferences;

    }

    @Override
    public void update(Conference conference) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceQuery = connection
                     .prepareStatement(sqlRequestBundle.getString("conference.update"), Statement.RETURN_GENERATED_KEYS);
             PreparedStatement reportQuery = connection
                     .prepareStatement((sqlRequestBundle.getString("report.update")))) {

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            conferenceQuery.setString(1, conference.getTopic());
            conferenceQuery.setString(2, conference.getLocation());
            conferenceQuery.setTimestamp(3, Timestamp.valueOf(conference.getDateTime()));
            conferenceQuery.setLong(4, conference.getId());
            conferenceQuery.executeUpdate();

            for (Report report : conference.getReports()) {
                reportQuery.setString(1, report.getTopic());
                LOG.debug("report id:" + report.getId());

                reportQuery.setTimestamp(2, Timestamp.valueOf(report.getDateTime()));
                reportQuery.setString(3, report.getSpeakerName());
                reportQuery.setString(4, report.getSpeakerSurname());
                reportQuery.setLong(5, report.getSpeakerId());
                reportQuery.setLong(6, report.getId());
                reportQuery.addBatch();
            }
            reportQuery.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Conference update failed: " + e);
            throw new RuntimeException();
        }
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
            throw new RuntimeException();
        }
    }

    @Override
    public boolean addNew(Conference conference) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceQuery = connection
                     .prepareStatement(sqlRequestBundle.getString("conference.insert"), Statement.RETURN_GENERATED_KEYS);
             PreparedStatement reportQuery = connection
                     .prepareStatement((sqlRequestBundle.getString("report.insert")))) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
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
                    reportQuery.setTimestamp(4, Timestamp.valueOf(report.getDateTime()));
                    reportQuery.addBatch();
                }
            }
            reportQuery.executeBatch();
            connection.commit();
            return true;
        } catch (SQLException e) {
            LOG.error("Conference inserting failed: " + e);
        }
        return false;
    }


    @Override
    public List<SubscriptionDTO> getSubscriptionsList(String language) {
        List<SubscriptionDTO> subscriptions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(sqlRequestBundle.getString("subscription.get.all"))
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subscriptions.add(new SubscriptionDtoMapper().mapToObject(resultSet,language));
            }
            return subscriptions;
        } catch (SQLException e) {
            LOG.error("Cant get subscriptions: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Long> getAllConferenceIdsInSubscriptions() {
        List<Long> conferenceIds = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("conferences.get.all.conference.ids.in.subscriptions"))) {
            ResultSet resultSet = conferenceStatement.executeQuery();

            while (resultSet.next()) {
                conferenceIds.add(resultSet.getLong("id_conference"));
            }
        } catch (SQLException e) {
            LOG.error("Cant get get All Conference Ids In Subscriptions: " + e);
        }
        return conferenceIds;

    }

    @Override
    public List<Conference> getPaginatedList(int begin, int recordsPerPage,String language) {
        List<Conference> paginatedList;
        ConferenceMapper conferenceMapper = new ConferenceMapper();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(sqlRequestBundle.getString("conferences.select.paginated"))) {
            statement.setInt(1, begin);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            paginatedList = conferenceMapper.mapToList(resultSet, language);
            return paginatedList;
        } catch (SQLException e) {
            LOG.error("Cant get paginated list: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public int getConferencesAmount() {
        int conferenceAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("conferences.count"))) {
            ResultSet resultSet = conferenceStatement.executeQuery();
            if (resultSet.next()) {
                conferenceAmount = resultSet.getInt(1);
            }
            return conferenceAmount;
        } catch (SQLException e) {
            LOG.error("Cant count conferences: " + e);
            throw new RuntimeException();
        }
    }
}

