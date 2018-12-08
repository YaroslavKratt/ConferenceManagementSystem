package ua.com.training.model.dao;

import java.util.List;

public interface DAO<T> {
    T getById();
    List<T> getAll();
    void update(T item);
    void delete(T item);
    boolean addNew(T item);


}
