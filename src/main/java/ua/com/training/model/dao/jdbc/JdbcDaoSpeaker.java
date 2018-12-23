package ua.com.training.model.dao.jdbc;

import ua.com.training.model.dao.SpeakerDao;
import ua.com.training.model.entity.Speaker;

import java.util.List;

public class JdbcDaoSpeaker implements SpeakerDao {


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
}
