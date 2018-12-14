package ua.com.training.model.dao.mappers;

import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper implements Mapper<Report> {




    @Override
    public Report mapToObject(ResultSet resultSet) throws SQLException {
        Report report = new Report();

        report.setTopic(resultSet.getString("report_topic"));
        report.setRegestratedAmount(resultSet.getInt("registrated_participants_amount"));
        report.setVisitorsAmount(resultSet.getInt("visited_participants_amount"));
        report.setDateTime(resultSet.getTimestamp("report_datetime").toLocalDateTime());
        report.setSpeakerName(resultSet.getString("speaker_name"));
        report.setSpeakerSurname(resultSet.getString("speaker_surname"));
        return report;

    }

}
