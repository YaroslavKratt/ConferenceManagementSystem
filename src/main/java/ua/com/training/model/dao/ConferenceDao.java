package ua.com.training.model.dao;

import ua.com.training.model.dto.ConferenceDTO;
import ua.com.training.model.dto.SubscriptionDTO;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;

import java.util.List;
import java.util.Map;

public interface ConferenceDao extends DAO<Conference> {

    List<Conference> getAll(String language);


    void update(ConferenceDTO conference);

    boolean addNew(ConferenceDTO conference);

    List<SubscriptionDTO> getSubscriptionsList(String language);

    List<Long> getAllConferenceIdsInSubscriptions();


    List<Conference> getPaginatedConferences(int begin, int recordsPerPage, String language);

    int getConferencesAmount();

    List<Conference> getPaginatedPastConferences(int begin, int recordsPerPage, String language);

    List<Conference> getPaginatedFutureConferences(int begin, int recordsPerPage, String language);

    int getFutureConferencesAmount();

    int getPastConferencesAmount();
}
