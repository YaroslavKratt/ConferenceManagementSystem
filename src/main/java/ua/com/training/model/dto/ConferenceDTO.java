package ua.com.training.model.dto;

import ua.com.training.model.entity.Conference;
import ua.com.training.model.entity.Report;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConferenceDTO {
    private long id;
    private String topicEn;
    private String topicUa;
    private String locationEn;
    private String locationUa;
    private LocalDateTime dateTime;
    private List<ReportDTO> reports = new ArrayList<>();

    public ConferenceDTO() {
    }

    public ConferenceDTO(Conference conferenceEn_us, Conference conferenceUk_ua) {
        this.id = conferenceEn_us.getId();
        this.locationEn = conferenceEn_us.getTopic();
        this.topicUa = conferenceUk_ua.getTopic();
        this.locationEn = conferenceEn_us.getLocation();
        this.locationUa = conferenceUk_ua.getLocation();
        this.dateTime = conferenceEn_us.getDateTime();
        this.reports = Conference.toReportDTOList(conferenceEn_us.getReports(), conferenceUk_ua.getReports());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicUa() {
        return topicUa;
    }

    public void setTopicUa(String topicUa) {
        this.topicUa = topicUa;
    }

    public String getLocationEn() {
        return locationEn;
    }

    public void setLocationEn(String locationEn) {
        this.locationEn = locationEn;
    }

    public String getLocationUa() {
        return locationUa;
    }

    public void setLocationUa(String locationUa) {
        this.locationUa = locationUa;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<ReportDTO> getReports() {
        return reports;
    }

    public void setReports(List<ReportDTO> reports) {
        this.reports = reports;
    }

    public String getTopicEn() {
        return topicEn;
    }

    public void setTopicEn(String topicEn) {
        this.topicEn = topicEn;
    }
}
