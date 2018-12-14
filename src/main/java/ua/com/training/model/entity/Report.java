package ua.com.training.model.entity;

import java.time.LocalDateTime;

public class Report {
    private long id;
    private String topic;
    private User speaker;
    private int regestratedAmount;
    private int visitorsAmount;
    private LocalDateTime dateTime;
    private String speakerName;
    private String speakerSurname;

    public Report() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public User getSpeaker() {
        return speaker;
    }

    public void setSpeaker(User speaker) {
        this.speaker = speaker;
    }

    public int getRegestratedAmount() {
        return regestratedAmount;
    }

    public void setRegestratedAmount(int registratedAmount) {
        this.regestratedAmount = registratedAmount;
    }

    public int getVisitorsAmount() {
        return visitorsAmount;
    }

    public void setVisitorsAmount(int visitorsAmount) {
        this.visitorsAmount = visitorsAmount;
    }

    @Override
    public  String toString() {
        return "Report: "
                + " Topic: " + topic
                + " Speaker: " + speakerName + " " + speakerSurname + "\n"
                + " Registrated amount: " + regestratedAmount
                + " Visitors amount: " + visitorsAmount + "\n";
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSpeakerSurname() {
        return speakerSurname;
    }

    public void setSpeakerSurname(String speakerSurname) {
        this.speakerSurname = speakerSurname;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
