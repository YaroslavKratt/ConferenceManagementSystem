package ua.com.training.model.dao.mappers;

import ua.com.training.model.dto.SubscriptionDTO;
import ua.com.training.model.entity.Report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionDtoMapper implements Mapper<SubscriptionDTO> {
    @Override
    public SubscriptionDTO mapToObject(ResultSet resultSet, String language) throws SQLException {
        return new SubscriptionDTO.Builder()
                .setConferenceDateTime(resultSet.getTimestamp("conference_timestamp").toLocalDateTime())
                .setConferenceLocation(resultSet.getString("conference_location_" + language))
                .setConferenceTopic(resultSet.getString("conference_topic_" + language))
                .setReportTopic(resultSet.getString("report_topic_" + language))
                .setReportDateTime(resultSet.getTimestamp("report_datetime").toLocalDateTime())
                .setUserEmail(resultSet.getString("user_email"))
                .setUserName(resultSet.getString("user_name_" + language))
                .setSpeakerSurname(resultSet.getString("speaker_surname_" + language))
                .setSpeakerName(resultSet.getString("speaker_name_" + language))
                .setUserSurname(resultSet.getString("user_surname_" + language))
                .setReportId(resultSet.getLong("id_report"))
                .setConferenceId(resultSet.getLong("id_conference"))
                .build();
    }


}
