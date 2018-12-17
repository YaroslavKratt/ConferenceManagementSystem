package ua.com.training.model.services;

import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.dao.UserDao;
import ua.com.training.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

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
    public User.Role getUserRole(long id) {
        return userDao.getUserRole(id);
    }

    public boolean signUpUser(User user) {

        return userDao.addNew(user);
    }

    public long getUserId(String email) {
        return userDao.getByEmail(email).getId();
    }

    public List<Long> getUserSubscriptionsIds(long userId) {
        return userDao.getUserSubscriptionsIds(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public void changeRole(long id, User.Role role) {
        userDao.changeRole(id,role);
    }
}
