package ua.com.training.model.dao;

import ua.com.training.model.entity.User;

import java.util.List;

public interface UserDao extends DAO<User> {


    User getByEmail(String email);

    boolean checkUserExist(String email);

    boolean checkUserPassword(String email, String password);

    User.Role getUserRole(String email);
    User.Role getUserRole(long id);


    List<Long> getUserSubscriptionsIds(long userId);

    void changeRole(long id, User.Role role);

    String getNameById(long id);

    String getSurnameById(long id);

    List<String> getUserSubscriptedEmails();


    boolean alreadyVoted(long userId, long speakerId);
}
