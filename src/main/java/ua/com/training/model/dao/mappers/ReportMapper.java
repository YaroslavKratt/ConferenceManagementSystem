package ua.com.training.model.dao.mappers;

import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper implements Mapper<Report> {


    @Override
    public Report mapToObject(ResultSet resultSet) throws SQLException {
        return new Report.Builder()
                .setId(resultSet.getLong("id_report"))
                .setTopic(resultSet.getString("report_topic"))
                .setRegestratedAmount(resultSet.getInt("registrated_participants_amount"))
                .setVisitorsAmount(resultSet.getInt("visited_participants_amount"))
                .setDateTime(resultSet.getTimestamp("report_datetime").toLocalDateTime())
                .setSpeakerName(resultSet.getString("speaker_name"))
                .setSpeakerSurname(resultSet.getString("speaker_surname"))
                .build();

    }

}
