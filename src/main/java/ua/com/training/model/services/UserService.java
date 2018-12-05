package ua.com.training.model.services;

import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.entity.User;

public class UserService {
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    public static String getRole(String email) {
        return daoFactory.createUserDao().getByEmail(email).getRole().toString().toLowerCase();

    }

    public static boolean checkUserExist(String email) {
        return daoFactory.createUserDao().checkUserExist(email);
    }
}
