package ru.job4j.persistent;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Item;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class DBStore does a lot of actions via Hibernate.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 05.10.2019.
 */
public class DBStore implements Store {

    private static final DBStore INSTANCE = new DBStore();
    private final SessionFactory factory;

    public DBStore() {
        this.factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Item item) {
        Session session = this.factory.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
            this.factory.close();
        }
    }

    @Override
    public void createOrUpdate(Item item) throws SessionException {
        Session session = this.factory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Item item) {
        Session session = this.factory.openSession();
        try {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Item item) {
        Session session = this.factory.openSession();
        try {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Map<Integer, Item> getAll() {
        Map<Integer, Item> itemsMap = new TreeMap<>();
        Session session = this.factory.openSession();
        try {
            session.beginTransaction();
            for (Item item : (List<Item>) session.createQuery("from Item").list()) {
                itemsMap.put(item.getId(), item);
            }
            session.getTransaction().commit();
        } catch (TransactionException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return itemsMap;
    }
}
