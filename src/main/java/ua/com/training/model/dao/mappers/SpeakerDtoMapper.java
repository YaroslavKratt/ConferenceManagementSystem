package ua.com.training.model.dao.mappers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.training.controller.commands.CatalogOfSpeakersCommand;
import ua.com.training.model.dto.SpeakerDTO;
import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.Speaker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SpeakerDtoMapper {
    private final static Logger LOG = LogManager.getLogger(SpeakerDtoMapper.class);

    public List<SpeakerDTO> mapToSpeakersListWithReports(ResultSet resultSet) throws SQLException {
        List<SpeakerDTO> speakersDto = new ArrayList<>();
        Set<Speaker> speakers = new HashSet<>();
        ReportMapper reportMapper = new ReportMapper();
        SpeakerMapper speakerMapper = new SpeakerMapper();
        Map<Speaker, List<Report>> speakerReports = new HashMap<>();
        Speaker speaker;

        while (resultSet.next()) {
            speaker = speakerMapper.mapToObject(resultSet);
            speakers.add(speaker);
            speakerReports.putIfAbsent(speaker, new ArrayList<>());
            speakerReports.get(speaker).add(reportMapper.mapToObject(resultSet));
        }
         speakers.forEach(currentSpeaker-> speakersDto.add(new SpeakerDTO(currentSpeaker,speakerReports.get(currentSpeaker))));
        return speakersDto;
    }
}
