package ru.job4j.logic;

import ru.job4j.models.Item;

import java.util.Map;

/**
 * Interface Validate.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 05.10.2019.
 */
public interface Validate {

    void add(Item item);

    void createOrUpdate(Item item);

    void update(Item item);

    void delete(Item item);

    Map<Integer, Item> getAll();
}
