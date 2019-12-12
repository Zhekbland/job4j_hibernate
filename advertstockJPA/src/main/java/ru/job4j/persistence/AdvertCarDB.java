package ru.job4j.persistence;

import org.hibernate.*;
import ru.job4j.models.Car;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdvertCarDB implements IStock<Car> {

    private static final AdvertCarDB INSTANCE = new AdvertCarDB();
    private final SessionFactory sessionFactory = DB.getInstance().getSessionFactory();

    public AdvertCarDB() {
        super();
    }

    public static AdvertCarDB getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Car car) {
        this.tx(session -> {
            session.save(car);
            return null;
        });
    }

    @Override
    public void update(Car car) {
        this.tx(session -> {
            session.update(car);
            return null;
        });
    }

    @Override
    public void delete(Car car) {
        this.tx(session -> {
            session.delete(car);
            return null;
        });
    }

    @Override
    public Map<Integer, Car> getAll() {
        return (Map<Integer, Car>) this.tx(session -> session.createQuery("from Car ").list()
                .stream()
                .collect(Collectors.toMap(Car::getId, car -> car)));
    }

    @Override
    public Car getById(int id) {
        return this.tx(session -> session.get(Car.class, id));
    }

    @Override
    public Car isCredentional(String param1, String param2) {
        return null;
    }

    /**
     * This method wraps repetitive actions and removes duplicating of code.
     * @param command - function that accepts one argument and produces a result.
     * @param <T> - type of return object.
     * @return - result of actions.
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.sessionFactory.openSession();
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