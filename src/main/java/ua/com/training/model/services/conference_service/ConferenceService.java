package ua.com.training.model.services.conference_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.model.dao.ConferenceDao;
import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.dto.ConferenceDTO;
import ua.com.training.model.dto.SpeakerDTO;
import ua.com.training.model.entity.Conference;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConferenceService {
    private static final Logger LOG = LogManager.getLogger(ConferenceService.class);

    private static ConferenceDao conferenceDao = DaoFactory.getInstance().createConferenceDao();

    public boolean addConference(ConferenceDTO conference) {
        return conferenceDao.addNew(conference);
    }

    public boolean deleteConference(Long id) {
        return conferenceDao.delete(id);
    }

    public Conference getConferenceById(long conferenceId, String language) {
        return conferenceDao.getById(conferenceId, language);
    }

    public List<Conference> getPaginatedList(int begin, int recordsPerPage, String language) {
        return sortPaginatedConferencesByDateTime(conferenceDao.getPaginatedConferences(begin, recordsPerPage, language));
    }

    public List<Conference> getSortedPaginatedConferences(FilterSortType type, int begin, int recordsPerPage, String language) {
        Map<FilterSortType, PaginatedFilterSort> filterSortMap = new HashMap<>();

        filterSortMap.put(FilterSortType.ALL, () -> getPaginatedList(begin, recordsPerPage, language));
        filterSortMap.put(FilterSortType.PAST, () -> getPaginatedPastConferences(begin, recordsPerPage, language));
        filterSortMap.put(FilterSortType.FUTURE, () -> getPaginatedFutureConferences(begin, recordsPerPage, language));

        return filterSortMap.get(type).filterSort();


    }

    private List<Conference> getPaginatedPastConferences(int begin, int recordsPerPage, String language) {
        return sortPaginatedConferencesByDateTime(conferenceDao.getPaginatedPastConferences(begin, recordsPerPage, language));
    }

    private List<Conference> getPaginatedFutureConferences(int begin, int recordsPerPage, String language) {
        return sortPaginatedConferencesByDateTime(conferenceDao.getPaginatedFutureConferences(begin, recordsPerPage, language));
    }

    public int getConferencesAmount() {
        return conferenceDao.getConferencesAmount();
    }
    public int getConferencesAmount(FilterSortType type) {
        Map<FilterSortType, ConferenceAmount> filteredSorted = new HashMap<>();
        filteredSorted.put(FilterSortType.ALL, this::getConferencesAmount);
        filteredSorted.put(FilterSortType.PAST, this::getPastConferencesAmount);
        filteredSorted.put(FilterSortType.FUTURE, this::getFutureConferencesAmount);

        return filteredSorted.get(type).getAmount();
    }

    private List<Conference> sortPaginatedConferencesByDateTime(List<Conference> conferences) {
        Comparator<Conference> byDateTime = Comparator.comparing(Conference::getDateTime);
        conferences.sort(byDateTime);
        return conferences;
    }
    private int getFutureConferencesAmount(){
        return conferenceDao.getFutureConferencesAmount();
    }
    private int getPastConferencesAmount(){
        return conferenceDao.getPastConferencesAmount();
    }

    public void update(ConferenceDTO conference) {
        conferenceDao.update(conference);
    }


}
