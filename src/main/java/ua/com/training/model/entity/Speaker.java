package ua.com.training.model.entity;

import java.util.Objects;

public class Speaker extends User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private double speakerBonus;
    private double speakerRating;

    private Speaker(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.speakerBonus = builder.speakerBonus;
        this.speakerRating = builder.speakerRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speaker speaker = (Speaker) o;
        return id == speaker.id &&
                Double.compare(speaker.speakerBonus, speakerBonus) == 0 &&
                Double.compare(speaker.speakerRating, speakerRating) == 0 &&
                name.equals(speaker.name) &&
                surname.equals(speaker.surname) &&
                email.equals(speaker.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, speakerBonus, speakerRating);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public double getSpeakerBonus() {
        return speakerBonus;
    }

    public double getSpeakerRating() {
        return speakerRating;
    }

    public static class Builder {
        private long id;
        private String name;
        private String surname;
        private String email;
        private double speakerBonus;
        private double speakerRating;

        public Builder setBonus(double speakerBonus) {
            this.speakerBonus = speakerBonus;
            return this;
        }

        public Builder setRating(double speakerRating) {
            this.speakerRating = speakerRating;
            return this;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Speaker build() {
            return new Speaker(this);
        }
    }
}
