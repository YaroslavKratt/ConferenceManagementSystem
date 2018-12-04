package ua.com.training.model.entity;

import java.math.BigInteger;

/**
 *
 */
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private Role role;
    private BigInteger speakerBonus;
    private double speakerRating;

    User() {    }

    private User(Builder userBuilder) {
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.surname = userBuilder.surname;
        this.email = userBuilder.email;
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getEmail() { return email; }

    public Role getRole() { return role; }

    public BigInteger getSpeakerBonus() { return speakerBonus; }

    public double getSpeakerRating() { return speakerRating; }

    public enum Role {
        ADMIN("admin"),
        SPEAKER("speaker"),
        USER("user"),
        GUEST("guest");

        private String role;

        Role(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }


    public static class Builder {
        private long id;
        private String name;
        private String surname;
        private String email;
        private Role role;
        private BigInteger speakerBonus;
        private double speakerRating;


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

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setSpeakerBonus(BigInteger speakerBonus) {
            this.speakerBonus = speakerBonus;
            return this;
        }

        public Builder setSpeakerRating(double speakerRating) {
            this.speakerRating = speakerRating;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}
