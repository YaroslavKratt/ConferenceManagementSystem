package ua.com.training.model.entity;

/**
 *
 */
public class User {
    protected long id;
    protected String name;
    protected String surname;
    protected String email;
    protected Role role;

    protected String password;


    protected User(Builder userBuilder) {
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.surname = userBuilder.surname;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.role = userBuilder.role;
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

    public Role getRole() {
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
                + " Name: " + name
                + " Surname: " + surname
                + " Role: " + role
                + " Email: " + email;

    }


    public enum Role {
        ADMIN("admin"),
        SPEAKER("speaker"),
        USER("user"),
        GUEST("guest");

        private String role;

        Role(String role) {
            this.role = role;
        }

        public String getStringRole() {
            return role;
        }

    }

    public static class Builder {
        protected long id;
        protected String name;
        protected String surname;
        protected String email;
        protected Role role;
        protected String password;


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

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }



        public User build() {
            return new User(this);
        }

    }
}
