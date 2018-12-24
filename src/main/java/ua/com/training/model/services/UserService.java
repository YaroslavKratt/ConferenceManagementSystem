package ua.com.training.model.services;

import ua.com.training.model.dao.DaoFactory;
import ua.com.training.model.dao.UserDao;
import ua.com.training.model.entity.User;

import java.util.List;

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

    public String getNameById(long id) {
        return userDao.getNameById(id);
    }

    public String getSurnameById(long id) {
        return userDao.getSurnameById(id);

    }

    public List<String> getUserSubscriptedEmails() {
        return userDao.getUserSubscriptedEmails();
    }

    public String getNameByEmail(String userEmail) {
        return userDao.getByEmail(userEmail).getName();
    }
    public String getSurnameByEmail(String userEmail) {
        return userDao.getByEmail(userEmail).getSurname();
    }

    public boolean alreadyVoted(String email,long speakerId) {
        return userDao.alreadyVoted(getUserId(email), speakerId);
    }
}
