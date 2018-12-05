package ua.com.training.model.dao.jdbc;

import ua.com.training.model.dao.UserDao;
import ua.com.training.model.entity.User;

import java.sql.Connection;
import java.util.List;

public class JdbcUserDao implements UserDao {


    @Override
    public User getByid() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User item) {

    }

    @Override
    public void delete(User item) {

    }

    boolean checkUserExist(){
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}
