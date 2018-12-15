package ua.com.training.model.dao;

import ua.com.training.model.entity.User;

import java.util.List;

public interface UserDao extends DAO<User> {


    User getByEmail(String email);

    boolean checkUserExist(String email);

    boolean checkUserPassword(String email, String password);

    User.Role getUserRole(String email);

    List<Long> getUserSubscriptionsIds(long userId);
}
