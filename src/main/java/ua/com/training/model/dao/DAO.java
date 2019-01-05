package ua.com.training.model.dao;

import java.util.List;

public interface DAO<T> {
    T getById(long id, String language);
    List<T> getAll(String language);
    void update(T item);
    boolean delete(long id);
    boolean addNew(T item);


}
