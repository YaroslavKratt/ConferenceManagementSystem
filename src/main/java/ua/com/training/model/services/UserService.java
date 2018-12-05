package ua.com.training.model.services;

import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.entity.User;

import javax.servlet.http.HttpSessionBindingEvent;

public class UserService {
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    public static String getRoleString(String email) {
        return daoFactory.createUserDao().getByEmail(email).getRole().toString().toLowerCase();

    }

    public static boolean checkUserExist(String email) {
        return daoFactory.createUserDao().checkUserExist(email);
    }

    public static boolean checkPassword(String email, String password) { return daoFactory.createUserDao().checkUserPassword(email, password);
    }

    public static User.Role getUserRole(String email) { return daoFactory.createUserDao().getUserRole(email);
    }
}
