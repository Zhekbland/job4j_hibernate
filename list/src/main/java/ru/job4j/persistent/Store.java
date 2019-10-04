package ru.job4j.persistent;

import ru.job4j.models.Item;

import java.util.Map;

public interface Store {

    void add(Item item);
    
    void createOrUpdate(Item item);

    void update(Item item);
    
    void delete(Item item);
    
    Map<Integer, Item> getAll();
}
