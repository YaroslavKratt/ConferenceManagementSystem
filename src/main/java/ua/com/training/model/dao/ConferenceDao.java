package ua.com.training.model.dao;

import ua.com.training.model.dto.SubscriptionDTO;
import ua.com.training.model.entity.Conference;

import java.util.List;

public interface ConferenceDao extends DAO<Conference> {
     List<SubscriptionDTO> getAllSubscriptions();
}
