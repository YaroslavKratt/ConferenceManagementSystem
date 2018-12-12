package ua.com.training.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conference {
    private long id;
    private String topic;
    private String location;
    private LocalDateTime dateTime;
    private List<Report> reports = new ArrayList<>();

    public void addReport(Report report) {
        reports.add(report);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {

        return "Conference: "
                + " Id: " + id
                + " Topic: " + topic
                + " Location: " + location
                + reports.stream().map(Report::toString).reduce("\n", String::concat) + "\n";
    }


}
