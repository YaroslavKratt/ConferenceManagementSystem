package ua.com.training.model.services;

import java.util.ResourceBundle;

public enum Resources {
    SQL_REQUESTS {
        {
            this.bundle = ResourceBundle.getBundle("sql_requests");
        }
    },
    DB_UTILS {
        {
            this.bundle = ResourceBundle.getBundle("db_utils");
        }
    },
    PATHS {
        {
            this.bundle = ResourceBundle.getBundle("paths");
        }
    },
    MESSAGES{
        {
            this.bundle = ResourceBundle.getBundle("messages");
        }
    };

    ResourceBundle bundle;

    public ResourceBundle getBundle() {
        return bundle;
    }
}
