package ua.com.training.model.services;

import ua.com.training.model.dao.SpeakerDao;
import ua.com.training.model.dao.jdbc.JdbcDaoFactory;
import ua.com.training.model.entity.Speaker;

import java.util.List;

public class SpeakerService {
    private SpeakerDao speakerDao = new JdbcDaoFactory().createSpeakerDao();
    public List<Speaker> getAllSpeakers() {
        return speakerDao.getAll();

    }
}
