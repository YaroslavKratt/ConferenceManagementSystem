package ua.com.training.model.dao;

import ua.com.training.model.dto.SpeakerDTO;
import ua.com.training.model.entity.Speaker;

import java.util.List;

public interface SpeakerDao extends DAO<Speaker>{

    List<SpeakerDTO> getAllSpeakersWithReports(String language);

    Double getRating(long speakerId);

    boolean updateRatingAndBonus(long speakerId, Double rating, double calculateBonus);

    int getSpeakersAmount();


    List<SpeakerDTO> getPaginatedList(Integer begin, Integer recordsPerPage, String language);
}
