package ua.com.training.model.dao.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.ResourceEnum;
import ua.com.training.model.dao.SpeakerDao;
import ua.com.training.model.dao.mappers.SpeakerDtoMapper;
import ua.com.training.model.dto.SpeakerDTO;
import ua.com.training.model.entity.Speaker;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class JdbcSpeakerDao implements SpeakerDao {
    private static final Logger LOG = LogManager.getLogger(JdbcSpeakerDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlRequestBundle = ResourceBundle.getBundle(ResourceEnum.SQL_REQUESTS_BUNDLE.getBundleName());


    @Override
    public Speaker getById(long id) {
        return null;
    }

    @Override
    public List<Speaker> getAll() {
        return null;
    }

    @Override
    public void update(Speaker item) {

    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean addNew(Speaker item) {
        return false;
    }

    @Override
    public List<SpeakerDTO> getAllSpeakersWithReports() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("speaker.get.all.with.reports"))) {
            ResultSet resultSet = preparedStatement.executeQuery();

            return new SpeakerDtoMapper().mapToSpeakersListWithReports(resultSet);
        } catch (SQLException e) {
            LOG.error("Can`t get all speakers with reports: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public Double getRating(long speakerId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequestBundle.getString("speaker.get.speaker.rating"))) {
            preparedStatement.setLong(1, speakerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble(1);

        } catch (SQLException e) {
            LOG.error("Cant get speaker Rating: " + e);
            throw new RuntimeException();
        }
    }

        @Override
    public boolean updateRatingAndBonus(long speakerId, Double rating, double bonus) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection
                         .prepareStatement(sqlRequestBundle.getString("speaker.update.rating.and.bonus"))) {
                preparedStatement.setDouble(1, rating);
                preparedStatement.setDouble(2, bonus);
                preparedStatement.setLong(3,speakerId);
                preparedStatement.executeUpdate();
                return true;

            } catch (SQLException e) {
                LOG.error("Cant get speaker Rating: " + e);
                throw new RuntimeException();
            }

    }
}
