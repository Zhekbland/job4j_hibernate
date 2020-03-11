package ru.job4j.persistent;

import org.junit.Test;
import ru.job4j.models.Item;

import java.sql.Timestamp;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DBStoreTest {

    private final DBStore dbStore = DBStore.getInstance();

    @Test
    public void whenWeCreateAndGetUsers() {
        Item item = new Item();
        item.setDescription("Property1");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        this.dbStore.add(item);
        Map<Integer, Item> items = this.dbStore.getAll();
        assertThat(items.get(1).getDescription(), is("Property1"));
    }
}