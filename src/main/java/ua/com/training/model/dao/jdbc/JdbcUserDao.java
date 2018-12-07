package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.UserDao;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ResourceManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(JdbcUserDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle = ResourceManager.getBundle(ResourceManager.SQL_REQUESTS_BUNDLE_NAME);

    @Override
    public User getById() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User item) {

    }

    @Override
    public void delete(User item) {

    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ResourceManager.getProperty(sqlRequestBundle, "query.user.select.by.email"))
        ) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public boolean checkUserExist(String email) {
        return getByEmail(email) != null;
    }

    @Override
    public boolean checkUserPassword(String email, String password) {
        return getByEmail(email).getPassword().equals(password);

    }

    @Override
    public User.Role getUserRole(String email) {
        return getByEmail(email).getRole();
    }


    private User extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getLong("user_id"))
                .setEmail(resultSet.getString("user_email"))
                .setName(resultSet.getString("user_name"))
                .setSurname(resultSet.getString("user_surname"))
                .setRole(User.Role.valueOf(resultSet.getString("user_role")))
                .setPassword(resultSet.getString("user_password"))
                .build();
    }

}



