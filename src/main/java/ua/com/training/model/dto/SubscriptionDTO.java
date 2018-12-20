package ua.com.training.model.dto;

import java.time.LocalDateTime;

public class SubscriptionDTO {
    private String reportTopic;
    private LocalDateTime reportDateTime;
    private String speakerName;
    private String speakerSurname;
    private String conferenceTopic;
    private String conferenceLocation;
    private LocalDateTime conferenceDateTime;
    private String userName;
    private String userSurname;
    private String userEmail;

    private SubscriptionDTO(Builder builder) {
        this.reportTopic = builder.reportTopic;
        this.conferenceDateTime = builder.conferenceDateTime;
        this.conferenceLocation = builder.conferenceLocation;
        this.conferenceTopic = builder.conferenceTopic;
        this.reportDateTime = builder.reportDateTime;
        this.speakerName = builder.speakerName;
        this.speakerSurname =builder.speakerSurname;
        this.userEmail = builder.userEmail;
        this.userSurname = builder.userSurname;
        this.userName = builder.userName;
    }
    public String getReportTopic() {
        return reportTopic;
    }


    public LocalDateTime getReportDateTime() {
        return reportDateTime;
    }


    public String getSpeakerName() {
        return speakerName;
    }


    public String getSpeakerSurname() {
        return speakerSurname;
    }


    public String getConferenceTopic() {
        return conferenceTopic;
    }


    public String getConferenceLocation() {
        return conferenceLocation;
    }


    public LocalDateTime getConferenceDateTime() {
        return conferenceDateTime;
    }


    public String getUserName() {
        return userName;
    }


    public String getUserSurname() {
        return userSurname;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public static class Builder {
        private String reportTopic;
        private LocalDateTime reportDateTime;
        private String speakerName;
        private String speakerSurname;
        private String conferenceTopic;
        private String conferenceLocation;
        private LocalDateTime conferenceDateTime;
        private String userName;
        private String userSurname;
        private String userEmail;


        public Builder setReportTopic(String reportTopic) {
            this.reportTopic = reportTopic;
            return this;
        }

        public Builder setReportDateTime(LocalDateTime reportDateTime) {
            this.reportDateTime = reportDateTime;
            return this;
        }

        public Builder setSpeakerName(String speakerName) {
            this.speakerName = speakerName;
            return this;
        }

        public Builder setConferenceTopic(String conferenceTopic) {
            this.conferenceTopic = conferenceTopic;
            return this;
        }

        public Builder setConferenceLocation(String conferenceLocation) {
            this.conferenceLocation = conferenceLocation;
            return this;
        }

        public Builder setConferenceDateTime(LocalDateTime conferenceDateTime) {
            this.conferenceDateTime = conferenceDateTime;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }


        public Builder setUserSurname(String userSurname) {
            this.userSurname = userSurname;
            return this;
        }

        public Builder setSpeakerSurname(String speakerSurname) {
            this.speakerSurname = speakerSurname;
            return this;
        }
        public SubscriptionDTO build (){
            return new SubscriptionDTO(this);
        }
    }
}
