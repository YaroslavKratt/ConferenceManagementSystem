package ua.com.training.model.services;

import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.dao.UserDao;
import ua.com.training.model.entity.User;

public class UserService {
    private static UserDao userDao = DaoFactory.getInstance().createUserDao();

    public static String getRoleString(String email) {
        return userDao.getByEmail(email).getRole().toString().toLowerCase();

    }

    public boolean checkUserExist(String email) {
        return userDao.checkUserExist(email);
    }

    public boolean checkPassword(String email, String password) {
        return userDao.checkUserPassword(email, password);
    }

    public User.Role getUserRole(String email) {
        return userDao.getUserRole(email);
    }

    public boolean signUpUser(User user) {

        return userDao.addNew(user);
    }
}
