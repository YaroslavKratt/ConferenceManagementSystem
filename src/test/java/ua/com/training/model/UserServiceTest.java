package ua.com.training.model;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.training.model.services.UserService;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class UserServiceTest {
    private static UserService userService;

    @BeforeClass
    public static void init() {
        userService = new UserService();
    }

    @Test
    public void returnTrueIfUserWithSuchEmailExist() {
        String email = "kratt202@gmail.com";
        assertTrue(userService.checkUserExist(email));
    }

    @Test
    public void returnTrueIfUserWithSuchEmailNotExist() {
        String email = "email-not-exist@email.com";
        assertFalse(userService.checkUserExist(email));
    }
}
