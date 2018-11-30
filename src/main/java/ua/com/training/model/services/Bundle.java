package ua.com.training.model.services;

public enum Bundle {
    SQL_REQUESTS("ua.com.model.sql_request"),
    DB_UTILS("db_utils"),
    PATHS("paths"),
    MESSAGES("messagges");
    private String bundleName;

    Bundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getBundleName() {
        return bundleName;
    }
}
