package ru.job4j.persistent;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.models.Item;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class DBStore does a lot of actions via Hibernate.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 05.10.2019.
 */
public class DBStore implements Store {

    private static final DBStore INSTANCE = new DBStore();
    private final SessionFactory factory;

    public DBStore() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        this.factory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Item item) {
        this.tx(session -> {
            session.save(item);
            return null;
        });
    }

    @Override
    public void createOrUpdate(Item item) throws SessionException {
        this.tx(session -> {
            session.saveOrUpdate(item);
            return null;
        });
    }

    @Override
    public void update(Item item) {
        this.tx(session -> {
            session.save(item);
            return null;
        });
    }

    @Override
    public void delete(Item item) {
        this.tx(session -> {
            session.delete(item);
            return null;
        });
    }

    @Override
    public Map<Integer, Item> getAll() {
        return (Map<Integer, Item>) this.tx(session -> session.createQuery("from Item").list()
                .stream()
                .collect(Collectors.toMap(Item::getId, x -> x))
        );
    }

    /**
     * This method wraps repetitive actions and removes duplicating of code.
     * @param command - function that accepts one argument and produces a result.
     * @param <T> - type of return object.
     * @return - result of actions.
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
