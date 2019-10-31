package ru.job4j.persistence;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.models.Car;
import ru.job4j.models.carmodels.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdvertStockDB implements IStock<Car> {

    private static final AdvertStockDB INSTANCE = new AdvertStockDB();
    private final SessionFactory sessionFactory;

    public AdvertStockDB() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        this.sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static AdvertStockDB getInstance() {
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

    public void fillCarModel() {
        CarMake carMake = new CarMake("Toyota");
        BodyType bodyType = new BodyType("sedan");
        EngineType engineType = new EngineType("3.6");
        GearboxType gearboxType = new GearboxType("Auto");
        Model model = new Model("Camry", new CarMake(1));
        Picture picture = new Picture(new byte[1]);
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(carMake);
            session.save(model);
            session.save(bodyType);
            session.save(engineType);
            session.save(gearboxType);
            session.save(picture);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
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