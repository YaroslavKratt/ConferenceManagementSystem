package ua.com.training.model.dao.mappers;

import ua.com.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {



    @Override
    public User mapToObject(ResultSet resultSet) throws SQLException {

        return new User.Builder()
                .setId(resultSet.getLong("user_id"))
                .setName(resultSet.getString("user_name"))
                .setSurname(resultSet.getString("user_surname"))
                .setPassword(resultSet.getString("user_password"))
                .setEmail(resultSet.getString("user_email"))
                .setRole(User.Role.valueOf(resultSet.getString("user_role")))
                 .build();
    }
}
