package ru.job4j.logic;

import java.util.Map;

/**
 * Interface Validate.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 07.12.2019.
 */
public interface Validate<T> {
    boolean add(T obj);
    boolean delete(T obj);
    Map<Integer, T> getAll();
    boolean update(T obj);
    T getById(int id);
    T isCredentioanal(String email, String password);
}
