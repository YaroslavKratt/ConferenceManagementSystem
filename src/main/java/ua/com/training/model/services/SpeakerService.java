package ua.com.training.model.services;

import ua.com.training.model.ResourceEnum;
import ua.com.training.model.dao.SpeakerDao;
import ua.com.training.model.dao.jdbc.JdbcDaoFactory;
import ua.com.training.model.dto.SpeakerDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class SpeakerService {
    private final static ResourceBundle BUISNESS_LOGIC_BUNDLE = ResourceBundle.getBundle(ResourceEnum.BUSINESS_LOGIC.getBundleName());
    private SpeakerDao speakerDao = new JdbcDaoFactory().createSpeakerDao();

    public Map<Long, Integer> getRatingMapFrom(List<SpeakerDTO> speakers) {
        Map<Long, Integer> ratingMap = new HashMap<>();
        speakers.forEach(speaker -> ratingMap.put(speaker.getId(), (int) speaker.getRating()));
        return ratingMap;
    }


    public double calculateBonus(double rating) {
        return Double.parseDouble(BUISNESS_LOGIC_BUNDLE.getString("bonus." + (int) rating + ".star"));
    }


    public int getSpeakersAmount() {
        return speakerDao.getSpeakersAmount();
    }


    public List<SpeakerDTO> getPaginatedList(Integer begin, Integer recordsPerPage, String language) {
        return speakerDao.getPaginatedList(begin, recordsPerPage, language);
    }
}
