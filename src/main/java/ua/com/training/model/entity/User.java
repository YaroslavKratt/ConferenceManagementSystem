package ua.com.training.model.entity;

public class User {
    private String login;
    private String password;

    public enum Roles {
        ADMIN("admin"),
        SPEAKER("speaker"),
        USER("user"),
        GUEST("guest");

        private  String role;

        Roles(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }
}
