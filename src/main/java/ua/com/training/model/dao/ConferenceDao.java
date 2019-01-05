package ua.com.training.model.dao;

import ua.com.training.model.dto.SubscriptionDTO;
import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;

import java.util.List;
import java.util.Map;

public interface ConferenceDao extends DAO<Conference> {

    List<Conference> getAll(String language);


    List<SubscriptionDTO> getSubscriptionsList(String language);

    List<Long> getAllConferenceIdsInSubscriptions();


    List<Conference> getPaginatedList(int begin, int recordsPerPage, String language);

    int getConferencesAmount();
}
