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
    public Conference mapToObject(ResultSet resultSet, String language) throws SQLException {
        Conference conference = null;
        while (resultSet.next()) {
            if (Objects.isNull(conference)) {
                conference = mapToConferenceWithoutReports(resultSet, language);
            }
            conference.addReport(reportMapper.mapToObject(resultSet, language));
        }

        return conference;
    }

    private Conference mapToConferenceWithoutReports(ResultSet resultSet, String language) throws SQLException {
        Conference conference = new Conference();

        conference.setId(resultSet.getLong("id_conference"));
        conference.setLocation(resultSet.getString("conference_location_" + language));
        conference.setTopic(resultSet.getString("conference_topic_" + language));
        conference.setDateTime(resultSet.getTimestamp("conference_timestamp").toLocalDateTime());
        return conference;
    }

    //todo
    public List<Conference> mapToList(ResultSet conferencesWithReports, String language) throws SQLException {
        Set<Conference> conferences = new HashSet<>();
        Map<Long, List<Report>> conferenceReports = new HashMap<>();

        while (conferencesWithReports.next()) {
            conferences.add(mapToConferenceWithoutReports(conferencesWithReports, language));
            conferenceReports.putIfAbsent(conferencesWithReports.getLong("id_conference"), new ArrayList<>());
            conferenceReports.get(conferencesWithReports.getLong("id_conference"))
                    .add(reportMapper.mapToObject(conferencesWithReports, language));
        }
        conferences.forEach(conference -> conference.setReports(conferenceReports.get(conference.getId())));
        return new ArrayList<>(conferences);
    }
}


