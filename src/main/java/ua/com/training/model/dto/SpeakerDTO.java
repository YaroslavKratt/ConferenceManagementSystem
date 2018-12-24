package ua.com.training.model.dto;


import ua.com.training.model.entity.Report;
import ua.com.training.model.entity.Speaker;

import java.util.List;
import java.util.Objects;

public class SpeakerDTO {
    protected long id;
    protected String name;
    protected String surname;
    protected String email;
    private double bonus;
    private double rating;
    private List<Report> speakerReports;

    public SpeakerDTO(Speaker speaker, List<Report> reports) {
        this.id = speaker.getId();
        this.name = speaker.getName();
        this.surname = speaker.getSurname();
        this.bonus= speaker.getSpeakerBonus();
        this.rating = speaker.getSpeakerRating();
        this.speakerReports = reports;
    }
    public long getStringId() {
        return this.id;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public double getBonus() {
        return bonus;
    }

    public double getRating() {
        return rating;
    }

    public List<Report> getSpeakerReports() {
        return speakerReports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpeakerDTO that = (SpeakerDTO) o;
        return id == that.id &&
                Double.compare(that.bonus, bonus) == 0 &&
                Double.compare(that.rating, rating) == 0 &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, bonus, rating);
    }
}
