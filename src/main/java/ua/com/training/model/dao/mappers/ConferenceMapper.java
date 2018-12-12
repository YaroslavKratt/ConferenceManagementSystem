package ua.com.training.model.dao.mappers;

import ua.com.training.model.dao.jdbc.ConnectionPool;
import ua.com.training.model.entity.Conference;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConferenceMapper implements Mapper<Conference>  {

    @Override
    public Conference mapToObject(ResultSet resultSet) throws SQLException {
            Conference conference =  new Conference();

            conference.setId(resultSet.getLong("id_conference"));
            conference.setLocation(resultSet.getString("conference_location"));
            conference.setTopic(resultSet.getString("conference_topic"));
            conference.setDateTime(resultSet.getTimestamp("conference_timestamp").toLocalDateTime());
            return  conference;
        }
    }

