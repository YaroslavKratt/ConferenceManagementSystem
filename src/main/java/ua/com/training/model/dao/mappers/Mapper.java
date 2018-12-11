package ua.com.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    T mapToObject(ResultSet resultSet) throws SQLException;

}
