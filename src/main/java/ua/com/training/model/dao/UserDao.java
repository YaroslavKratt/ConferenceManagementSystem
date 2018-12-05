package ua.com.training.model.dao;

import ua.com.training.model.dao.DAO;
import ua.com.training.model.entity.User;

import java.util.List;

public interface UserDao extends DAO<User> {


    public User getByEmail(String email);
    boolean checkUserExist(String email);

}
