package ua.com.training.model.dao.mappers;

import ua.com.training.model.dto.SubscriptionDTO;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SubscriptionDtoMapper implements Mapper<SubscriptionDTO> {
    @Override
    public SubscriptionDTO mapToObject(ResultSet resultSet) throws SQLException {
        return new SubscriptionDTO.Builder()
                .setConferenceDateTime(resultSet.getTimestamp("conference_timestamp").toLocalDateTime())
                .setConferenceLocation(resultSet.getString("conference_location"))
                .setConferenceTopic(resultSet.getString("conference_topic"))
                .setReportTopic(resultSet.getString("report_topic"))
                .setReportDateTime(resultSet.getTimestamp("report_datetime").toLocalDateTime())
                .setUserEmail(resultSet.getString("user_email"))
                .setUserName(resultSet.getString("user_name"))
                .setSpeakerSurname(resultSet.getString("speaker_surname"))
                .setSpeakerName(resultSet.getString("speaker_name"))
                .setUserSurname(resultSet.getString("user_surname"))
                .setReportId(resultSet.getLong("id_report"))
                .setConferenceId(resultSet.getLong("id_conference"))
                .build();
    }

   public Map<String, Map<Long, List<Report>>> getMailSortedMap(ResultSet resultSet) throws SQLException {
        Map<String, Map<Long, List<Report>>> sortedMailMap = new HashMap<>();
        Set<String> emails = new HashSet<>();
        ReportMapper reportMapper = new ReportMapper();
        String currentEmail;
        Long currentConferenceId;

        while (resultSet.next()) {
            currentEmail = resultSet.getString("user_email");
            currentConferenceId = resultSet.getLong("id_conference");

            emails.add(currentEmail);
            sortedMailMap.putIfAbsent(currentEmail, new HashMap<>());
            sortedMailMap.get(currentEmail).putIfAbsent(currentConferenceId, new ArrayList<>());
            sortedMailMap.get(currentEmail).get(currentConferenceId).add(reportMapper.mapToObject(resultSet));
        }
        return sortedMailMap;
    }
}
