package ua.com.training.model.dao.mappers;

import ua.com.training.model.dto.SubscriptionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
                .build();
    }
}
