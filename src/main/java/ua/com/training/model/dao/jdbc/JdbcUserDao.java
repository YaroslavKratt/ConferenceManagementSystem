package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.com.training.model.dao.UserDao;
import ua.com.training.model.entity.User;
import ua.com.training.model.services.ResourceService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcUserDao implements UserDao {
    private static final Logger LOG = LogManager.getLogger(JdbcUserDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle = new ResourceService().getBundle(ResourceService.SQL_REQUESTS_BUNDLE_NAME);


    @Override
    public User getById(long id) {
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
    public void delete(long id) {

    }

    @Override
    public boolean addNew(User user) throws RuntimeException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("user.insert.new"))) {

            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getRole().toString());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            LOG.error("User was not added: " + e);
            return false;
        }
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("user.select.by.email"))
        ) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(e);
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

    @Override
    public List<Long> getUserSubscriptionsIds(long userId) {
        List<Long> userSubscriptionsIds = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement(sqlRequestBundle.getString("get.user.subscriptions.ids"))) {
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userSubscriptionsIds.add(resultSet.getLong("reports_id_report"));
            }
            return userSubscriptionsIds;
        } catch (SQLException e) {
            LOG.error("Can`y get list of user subscription: " + e);
            throw  new RuntimeException();
        }
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



