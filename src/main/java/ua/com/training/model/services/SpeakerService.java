package ua.com.training.model.services;

import ua.com.training.model.dao.SpeakerDao;
import ua.com.training.model.dao.jdbc.JdbcDaoFactory;
import ua.com.training.model.dto.SpeakerDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeakerService {
    private SpeakerDao speakerDao = new JdbcDaoFactory().createSpeakerDao();
    public List<SpeakerDTO> getAllSpeakersWithReports() {
        return speakerDao.getAllSpeakersWithReports();

    }
    public Map<Long,Integer> getRatingMapFrom(List<SpeakerDTO> speakers) {
        Map<Long,Integer> ratingMap = new HashMap<>();
        speakers.forEach(speaker -> ratingMap.put(speaker.getId(),(int)speaker.getRating()));
        return ratingMap;
    }
}
