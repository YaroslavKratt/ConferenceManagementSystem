package ua.com.training.model.dto;

import ua.com.training.model.entity.User;

/**
 *
 */
public class UserDTO {
    private long id;
    private String nameEn;
    private String nameUa;
    private String surnameEn;
    private String surnameUa;
    private String email;
    private User.Role role;
    private String password;


    private UserDTO(Builder userBuilder) {
        this.id = userBuilder.id;
        this.nameEn = userBuilder.nameEn;
        this.nameUa = userBuilder.nameUa;
        this.surnameEn = userBuilder.surnameEn;
        this.surnameUa = userBuilder.surnameUa;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.role = userBuilder.role;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameUa() {
        return nameUa;
    }

    public String getSurnameEn() {
        return surnameEn;
    }

    public String getSurnameUa() {
        return surnameUa;
    }

    public String getEmail() {
        return email;
    }

    public User.Role getRole() {
        return role;
    }


    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Id: " + id
                + " Name: " + nameEn
                + " Surname: " + surnameEn
                + " Role: " + role
                + " Email: " + email;

    }


    public static class Builder {
        private long id;
        private String nameEn;
        private String nameUa;
        private String surnameEn;
        private String surnameUa;
        private String email;
        private User.Role role;
        private String password;


        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setNameEn(String name) {
            this.nameEn = name;
            return this;
        }

        public Builder setNameUa(String name) {
            this.nameUa = name;
            return this;
        }

        public Builder setSurnameEn(String surname) {
            this.surnameEn = surname;
            return this;
        }

        public Builder setSurnameUa(String surname) {
            this.surnameUa = surname;
            return this;
        }

        public Builder setRole(User.Role role) {
            this.role = role;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }


        public UserDTO build() {
            return new UserDTO(this);
        }

    }
}
