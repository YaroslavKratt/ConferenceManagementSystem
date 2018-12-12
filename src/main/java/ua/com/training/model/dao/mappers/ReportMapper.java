package ua.com.training.model.dao.mappers;

import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper implements Mapper<Report> {




    @Override
    public Report mapToObject(ResultSet resultSet) throws SQLException {
        Report report = new Report();
        User speaker = new UserMapper().mapToObject(resultSet);

        report.setTopic(resultSet.getString("report_topic"));
        report.setRegestratedAmount(resultSet.getInt("registrated_participants_amount"));
        report.setVisitorsAmount(resultSet.getInt("visited_participants_amount"));
        report.setDateTime(resultSet.getTimestamp("report_datetime").toLocalDateTime());
        report.setSpeaker(speaker);
        return report;

    }

}
