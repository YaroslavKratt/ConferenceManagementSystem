package ua.com.training.model.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T getById();
    List<T> getAll();
    void update(T item);
    void delete(T item);


}
