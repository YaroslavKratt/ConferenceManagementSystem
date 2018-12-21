package ua.com.training.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    private long id;
    private String topic;
    private User speaker;
    private int regestratedAmount;
    private int visitorsAmount;
    private LocalDateTime dateTime;
    private String speakerName;
    private String speakerSurname;
    private long speakerId;

    private Report(Builder builder) {
        this.id = builder.id;
        this.topic = builder.topic;
        this.speaker = builder.speaker;
        this.speakerName = builder.speakerName;
        this.speakerSurname = builder.speakerSurname;
        this.dateTime = builder.dateTime;
        this.regestratedAmount = builder.registratedAmount;
        this.visitorsAmount = builder.visitorsAmount;
        this.speakerId = builder.speakerId;
    }

    public long getSpeakerId() {
        return speakerId;
    }

    public String getTopic() {
        return topic;
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

    public int getVisitorsAmount() {
        return visitorsAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getSpeakerSurname() {
        return speakerSurname;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public long getId() {
        return id;
    }
    public String getFormatedDateTime(){
        return dateTime.format(
                DateTimeFormatter.ofPattern("dd.mm.yyyy HH:MM"));
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Report: "
                + " Topic: " + topic
                + " Speaker: " + speakerName + " " + speakerSurname + "\n"
                + " Registrated amount: " + regestratedAmount
                + " Visitors amount: " + visitorsAmount + "\n";
    }

    public static class Builder {
        private long id;
        private String topic;
        private User speaker;
        private int registratedAmount;
        private int visitorsAmount;
        private LocalDateTime dateTime;
        private String speakerName;
        private String speakerSurname;
        private long speakerId;

        public Report build() {
            return new Report(this);
        }

        public Builder setSpeakerId(long speakerId) {
            this.speakerId = speakerId;
            return this;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder setSpeaker(User speaker) {
            this.speaker = speaker;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setRegistratedAmount(int registratedAmount) {
            this.registratedAmount = registratedAmount;
            return this;
        }

        public Builder setSpeakerName(String speakerName) {
            this.speakerName = speakerName;
            return this;
        }

        public Builder setSpeakerSurname(String speakerSurname) {
            this.speakerSurname = speakerSurname;
            return this;

        }

        public Builder setVisitorsAmount(int visitorsAmount) {
            this.visitorsAmount = visitorsAmount;
            return this;
        }

    }

}
