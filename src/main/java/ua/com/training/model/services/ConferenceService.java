package ua.com.training.model.services;

import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;

import java.util.List;

public class ConferenceService {
    private static ConferenceDao conferenceDao = DaoFactory.getInstance().createConferenceDao();

    public List<Conference> getAllConferences() {
        return conferenceDao.getAll();
    }

    public boolean addConference(Conference conference) {
        return conferenceDao.addNew(conference);
    }

    public boolean deleteConference(Long id) {
        return conferenceDao.delete(id);
    }

    public Conference getConferenceById(long conferenceId) {
        return conferenceDao.getById(conferenceId);
    }
    public List<Conference> getPaginatedList(int begin, int recordsPerPage) {
        return conferenceDao.getPaginatedList(begin,recordsPerPage);
    }

    public int getConferencesAmount() {
        return conferenceDao.getConferencesAmount();
    }

    public void update(Conference conference) {
         conferenceDao.update(conference);
    }


}
