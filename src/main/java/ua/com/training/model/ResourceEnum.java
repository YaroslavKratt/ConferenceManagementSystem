package ua.com.training.model;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceEnum {
    SQL_REQUESTS_BUNDLE {
        {
            this.bundleName = "sql_requests";
        }
    },
    ACCESS_BUNDLE {
        {
            this.bundleName = "access";
        }
    },
    PATHS_BUNDLE {
        {
            this.bundleName = "paths";
        }
    },
    MESSAGE_BUNDLE {
        {
            this.bundleName = "messages";
        }
    },
    REGEXP_BUNDLE {
        {
            this.bundleName = "regexp";
        }
    },
    EMAIL_BUNDLE {
        {
            this.bundleName = "email";
        }
    },
    BUISNESS_LOGIC {
        {
            this.bundleName="business_logic";
        }
    };

    String bundleName;

    public String getBundleName() {
        return bundleName;

    }
}