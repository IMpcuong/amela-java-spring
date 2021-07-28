package com.tutorial.repository;

import java.util.List;

public interface IGeneralRepository<T> {

    List<T> findAll();

    T findById(Long id);

    List<T> findByName(String word);

    void save(T t);

    void remove(Long id);
}
