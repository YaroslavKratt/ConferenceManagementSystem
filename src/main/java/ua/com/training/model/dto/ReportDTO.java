package ua.com.training.model.dto;

import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportDTO {
    private long id;
    private String topicEn;
    private String topicUa;
    private User speaker;
    private int regestratedAmount;
    private int comersAmount;
    private LocalDateTime dateTime;
    private long speakerId;
    private String speakerNameEn;
    private String speakerNameUa;
    private String getSpeakerSurnameEn;
    private String getSpeakerSurnameUa;


    public ReportDTO(Report reportEn, Report reportUa) {
        this.id = reportEn.getId();
        this.topicEn = reportEn.getTopic();
        this.topicUa = reportUa.getTopic();
        this.speaker = reportEn.getSpeaker();
        this.regestratedAmount = reportEn.getRegestratedAmount();
        this.comersAmount = reportEn.getComersAmount();
        this.dateTime = reportEn.getDateTime();
        this.speakerId = reportEn.getSpeakerId();

    }

    private ReportDTO(Builder builder) {
        this.id = builder.id;
        this.topicEn = builder.topicEn;
        this.topicUa = builder.topicUa;
        this.speaker = builder.speaker;
        this.dateTime = builder.dateTime;
        this.regestratedAmount = builder.registratedAmount;
        this.comersAmount = builder.comersAmount;
        this.speakerId = builder.speakerId;
        this.speakerNameEn = builder.speakerNameEn;
        this.speakerNameUa = builder.speakerNameUa;
        this.getSpeakerSurnameEn = builder.speakerSurnameEn;
        this.getSpeakerSurnameUa = builder.speakerSurnameUa;
    }

    public long getSpeakerId() {
        return speakerId;
    }

    public String getTopicEn() {
        return topicEn;
    }

    public String getTopicUa() {
        return topicUa;
    }

    public String getGetSpeakerSurnameEn() {
        return getSpeakerSurnameEn;
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

    public int getComersAmount() {
        return comersAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormatedDateTime() {
        return dateTime.format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:MM"));
    }

    public String getSpeakerNameUa() {
        return speakerNameUa;
    }

    public String getGetSpeakerSurnameUa() {
        return getSpeakerSurnameUa;
    }

    public String getSpeakerNameEn() {
        return speakerNameEn;
    }



    @Override
    public String toString() {
        return "ReportDTO: "
                + " id: " + id
                + " Topic: " + topicEn
                + " Registrated amount: " + regestratedAmount
                + " Visitors amount: " + comersAmount + "\n";
    }


    public static class Builder {
        private String topicEn;
        private long id;
        private String topicUa;
        private User speaker;
        private int registratedAmount;
        private int comersAmount;
        private LocalDateTime dateTime;
        private String speakerNameEn;
        private String speakerNameUa;
        private String speakerSurnameEn;
        private String speakerSurnameUa;

        private long speakerId;

        public ReportDTO build() {
            return new ReportDTO(this);
        }

        public Builder setSpeakerId(long speakerId) {
            this.speakerId = speakerId;
            return this;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setTopicEn(String topic) {
            this.topicEn = topic;
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


        public Builder setComersAmount(int comersAmount) {
            this.comersAmount = comersAmount;
            return this;
        }

        public Builder setTopicUa(String topicUa) {
            this.topicUa = topicUa;
            return this;
        }

        public Builder setSpeakerNameEn(String speakerNameEn) {
            this.speakerNameEn = speakerNameEn;
            return this;
        }

        public Builder setSpeakerNameUa(String speakerNameUa) {
            this.speakerNameUa = speakerNameUa;
            return this;
        }

        public Builder setSpeakerSurnameEn(String speakerSurnameEn) {
            this.speakerSurnameEn = speakerSurnameEn;
            return this;
        }

        public Builder setSpeakerSurnameUa(String speakerSurnameUa) {
            this.speakerSurnameUa = speakerSurnameUa;
            return this;
        }
    }

}
