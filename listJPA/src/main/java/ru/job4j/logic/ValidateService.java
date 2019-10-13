package ru.job4j.logic;

import ru.job4j.models.Item;
import ru.job4j.persistent.DBStore;
import ru.job4j.persistent.Store;

import java.util.Map;

/**
 * Class ValidateService will be validate.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 05.10.2019.
 */
public class ValidateService implements Validate {

    private final Store store = DBStore.getInstance();

    private static final ValidateService INSTANCE = new ValidateService();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Item item) {
        this.store.add(item);
    }

    @Override
    public void createOrUpdate(Item item) {
        this.store.createOrUpdate(item);
    }

    @Override
    public void update(Item item) {
        this.store.update(item);
    }

    @Override
    public void delete(Item item) {
        this.store.delete(item);
    }

    @Override
    public Map<Integer, Item> getAll() {
        return this.store.getAll();
    }
}
