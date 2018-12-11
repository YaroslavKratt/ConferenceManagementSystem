package ua.com.training.model.services;

import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.entity.Conference;

import java.util.List;

public class ConferenceService {
    private static ConferenceDao conferenceDao = DaoFactory.getInstance().createConferenceDao();

    public List<Conference> getAllConferences() {
        return conferenceDao.getAll();
    }
}
