package ua.com.training.model.dao;

import java.util.List;

public interface DAO<T> {
    T getById(long id);
    List<T> getAll();
    void update(T item);
    void delete(long id);
    boolean addNew(T item);


}
