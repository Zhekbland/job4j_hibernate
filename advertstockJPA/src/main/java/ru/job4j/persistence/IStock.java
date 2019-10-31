package ru.job4j.persistence;

import java.util.Map;

public interface IStock<T> {
    void add(T obj);

    void update(T obj);

    void delete(T obj);

    Map<Integer, T> getAll();
}
