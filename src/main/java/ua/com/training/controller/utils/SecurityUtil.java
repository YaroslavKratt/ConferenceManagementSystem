package ua.com.training.controller.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtil {
   private static final Logger LOG = LogManager.getLogger(SecurityUtil.class);
    //todo
    public static boolean isLogedIn() {
        return false;
    }

    public String hashPassword( String password)  {
        return BCrypt.hashpw(password,BCrypt.gensalt());

    }
}
