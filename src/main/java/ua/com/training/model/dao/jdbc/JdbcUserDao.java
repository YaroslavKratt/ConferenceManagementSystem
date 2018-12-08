package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
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
    private ResourceBundle sqlRequestBundle = new ResourceManager().getBundle(ResourceManager.SQL_REQUESTS_BUNDLE_NAME);

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
    public boolean addNew(User user) throws RuntimeException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("query.insert.user.new"))) {

            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getRole().toString());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("User was not added: " + e);
            return false;
        }
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("query.select.user.by.email"))
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
        return BCrypt.checkpw(password,getByEmail(email).getPassword());

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



