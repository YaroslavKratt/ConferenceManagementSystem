package ua.com.training.model.dao.mappers;

import ua.com.training.model.entity.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper implements Mapper<Report> {


    @Override
    public Report mapToObject(ResultSet resultSet, String language) throws SQLException {
        return new Report.Builder()
                .setId(resultSet.getLong("id_report"))
                .setTopic(resultSet.getString("report_topic_" + language))
                .setRegistratedAmount(resultSet.getInt("registrated_participants_amount"))
                .setComersAmount(resultSet.getInt("visited_participants_amount"))
                .setDateTime(resultSet.getTimestamp("report_datetime").toLocalDateTime())
                .setSpeakerName(resultSet.getString("user_name_" + language))
                .setSpeakerSurname(resultSet.getString("user_surname_" + language))
                .setSpeakerId(resultSet.getLong("speaker_id"))
                .build();

    }

}
