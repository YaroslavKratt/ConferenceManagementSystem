package ua.com.training.model.services;

import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.dto.ConferenceDTO;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;

import java.util.List;

public class ConferenceService {
    private static ConferenceDao conferenceDao = DaoFactory.getInstance().createConferenceDao();

    public boolean addConference(ConferenceDTO conference) {
        return conferenceDao.addNew(conference);
    }

    public boolean deleteConference(Long id) {
        return conferenceDao.delete(id);
    }

    public Conference getConferenceById(long conferenceId, String language) {
        return conferenceDao.getById(conferenceId,language);
    }
    public List<Conference> getPaginatedList(int begin, int recordsPerPage, String language) {
        return conferenceDao.getPaginatedList(begin,recordsPerPage, language);
    }

    public int getConferencesAmount() {
        return conferenceDao.getConferencesAmount();
    }

    public void update(ConferenceDTO conference) {
         conferenceDao.update(conference);
    }


}
