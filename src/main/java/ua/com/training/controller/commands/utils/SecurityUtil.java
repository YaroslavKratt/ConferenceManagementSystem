package ua.com.training.controller.commands.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

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
