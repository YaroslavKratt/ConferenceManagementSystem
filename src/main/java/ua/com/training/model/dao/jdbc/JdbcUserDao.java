package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.com.training.model.dao.UserDao;
import ua.com.training.model.dao.mappers.UserMapper;
import ua.com.training.model.entity.User;
import ua.com.training.model.ResourceEnum;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class JdbcUserDao implements UserDao {
    private static final Logger LOG = LogManager.getLogger(JdbcUserDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle = ResourceBundle.getBundle(ResourceEnum.SQL_REQUESTS_BUNDLE.getBundleName());


    @Override
    public User getById(long id) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("user.select.by.id"))) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserMapper().mapToObject(resultSet);
            }
            return user;

        } catch (SQLException e) {
            LOG.error("Cant get user  by id:" + e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("user.select.all"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new UserMapper().mapToObject(resultSet));
            }
            return users;
        } catch (SQLException e) {
            LOG.error("Cant get all users: " + e);
            throw new RuntimeException();
        }

    }

    @Override
    public void update(User item) {

    }

    @Override
    public boolean delete(long id) {
    return false;
    }

    @Override
    public boolean addNew(User user) throws RuntimeException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("user.insert.new"))) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().toString());
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
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("user.select.by.email"))) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new UserMapper().mapToObject(resultSet);
            }
            return user;

        } catch (SQLException e) {
            LOG.error("Cant get user  by email:" + e);
            throw new RuntimeException();
        }

    }

    @Override
    public boolean checkUserExist(String email) {
        return getByEmail(email) != null;
    }

    @Override
    public boolean checkUserPassword(String email, String password) {
        return BCrypt.checkpw(password, getByEmail(email).getPassword());

    }

    @Override
    public User.Role getUserRole(String email) {
        return getByEmail(email).getRole();
    }

    @Override
    public User.Role getUserRole(long id) {
        return getById(id).getRole();
    }

    @Override
    public List<Long> getUserSubscriptionsIds(long userId) {
        List<Long> userSubscriptionsIds = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();

             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("user.get.subscriptions.ids"))) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userSubscriptionsIds.add(resultSet.getLong("reports_id_report"));
            }
            return userSubscriptionsIds;
        } catch (SQLException e) {
            LOG.error("Can`y get list of user subscription: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void changeRole(long id, User.Role role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestBundle.getString("user.change.role"))) {

            preparedStatement.setString(1, role.toString());
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("User was not added: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public String getNameById(long id) {
        return getById(id).getName();
    }

    @Override
    public String getSurnameById(long id) {
        return getById(id).getSurname();
    }

    @Override
    public List<String> getUserSubscriptedEmails() {

        ArrayList<String> subscriptedEmails = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();

             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("user.get.subscripted.emails"))) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscriptedEmails.add(resultSet.getString("user_email"));
            }
            return subscriptedEmails;
        } catch (SQLException e) {
            LOG.error("Can`y get list of subscripted emails: " + e);
            throw new RuntimeException();
        }    }


}



