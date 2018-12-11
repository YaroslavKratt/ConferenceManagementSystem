package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.mappers.ConferenceMapper;
import ua.com.training.model.dao.mappers.ReportMapper;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ResourceService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcConferenceDao implements ConferenceDao {
    private final static Logger LOG = LogManager.getLogger(JdbcConferenceDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle = new ResourceService()
            .getBundle(ResourceService.SQL_REQUESTS_BUNDLE_NAME);

    @Override
    public Conference getById() {
        return null;
    }

    @Override
    public List<Conference> getAll() {
        List<Conference> conferences = new ArrayList<>();
        ConferenceMapper conferenceMapper = new ConferenceMapper();
        ReportMapper reportMapper = new ReportMapper();
        Map<Long, List<Report>> reportsById = new HashMap<>();
        Map<Long, User> speakersById = new HashMap<>();


        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("query.select.all.conferences"));
             PreparedStatement reportStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("query.select.all.reports.with.users"))) {

            connection.setAutoCommit(false);
            ResultSet conferenceResultSet = conferenceStatement.executeQuery();
            ResultSet reportResultSet = reportStatement.executeQuery();

            while (conferenceResultSet.next()) {
                conferences.add(conferenceMapper.mapToObject(conferenceResultSet));
                reportsById.put(conferenceResultSet.getLong("id_conference"), new ArrayList<>());
            }

            while (reportResultSet.next()) {
                reportsById.get(reportResultSet.getLong("id_conference")).add(reportMapper.mapToObject(reportResultSet));
            }
            LOG.info("reports by id: " + reportsById);
            for (Conference conference : conferences) {

                conference.setReports(reportsById.get(conference.getId()));
            }

            connection.commit();
            return conferences;
        } catch (SQLException e) {
            LOG.error("Cant get all conferences: " + e);
        }

        return null;
    }

    @Override
    public void update(Conference item) {

    }

    @Override
    public void delete(Conference item) {

    }

    @Override
    public boolean addNew(Conference item) {
        return false;
    }
}
