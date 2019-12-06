package ru.job4j.persistence;

import ru.job4j.models.Car;
import ru.job4j.models.carmodels.Picture;

import java.util.Map;

public interface IStock<T> {
    void add(T obj);

    void update(T obj);

    void delete(T obj);

    Map<Integer, T> getAll();

    T getById(int id);

    T isCredentional(String param1, String param2);
}
