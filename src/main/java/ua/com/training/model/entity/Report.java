package ua.com.training.model.entity;

public class Report {
    private String topic;
    private User speaker;
    private int regestratedAmount;
    private int visitorsAmount;

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
                + " Speaker: " + speaker.toString() + "\n"
                + " Registrated amount: " + regestratedAmount
                + " Visitors amount: " + visitorsAmount + "\n";
    }
}
