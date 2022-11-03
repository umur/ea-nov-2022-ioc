package edu.miu.eaplayground.Lab2Rest.service;

import java.util.List;
import java.util.Optional;

public interface GenService<T> {
    List<T> findAll();
    Optional<T> find(int id);
    T save(T item);
    void delete(int id);
}
