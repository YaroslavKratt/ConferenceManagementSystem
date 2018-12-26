package ua.com.training.model.dao.mappers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ConferenceMapper implements Mapper<Conference> {
    private static final Logger LOG = LogManager.getLogger(ConferenceMapper.class);
    private ReportMapper reportMapper = new ReportMapper();

    @Override
    public Conference mapToObject(ResultSet resultSet) throws SQLException {
        Conference conference = null;

        while (resultSet.next()){
        conference = mapToConferenceWithoutReports(resultSet);
        conference.addReport(reportMapper.mapToObject(resultSet));
        }
        return conference;
    }

    public Conference mapToConferenceWithoutReports(ResultSet resultSet) throws SQLException {
        Conference conference = new Conference();

        conference.setId(resultSet.getLong("id_conference"));
        conference.setLocation(resultSet.getString("conference_location"));
        conference.setTopic(resultSet.getString("conference_topic"));
        conference.setDateTime(resultSet.getTimestamp("conference_timestamp").toLocalDateTime());
        return conference;
    }

    public List<Conference> mapToList(ResultSet conferencesWithReports) throws SQLException {
        Set<Conference> conferences = new HashSet<>();
        Map<Long, List<Report>> conferenceReports = new HashMap<>();

        while (conferencesWithReports.next()) {
            conferences.add(mapToConferenceWithoutReports(conferencesWithReports));
            conferenceReports.putIfAbsent(conferencesWithReports.getLong("id_conference"), new ArrayList<>());
            conferenceReports.get(conferencesWithReports.getLong("id_conference"))
                             .add(reportMapper.mapToObject(conferencesWithReports));
        }
        conferences.forEach(conference -> conference.setReports(conferenceReports.get(conference.getId())));
        return new ArrayList<>(conferences);
    }
}


