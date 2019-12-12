package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.models.carmodels.*;

import java.util.function.Function;

public class CarModelDB {

    private static final CarModelDB INSTANCE = new CarModelDB();
    private final SessionFactory sessionFactory =  DB.getInstance().getSessionFactory();;

    public CarModelDB() {
    }

    public static CarModelDB getInstance() {
        return INSTANCE;
    }

    public void fillCarMake(CarMake carMake) {
        this.tx(session -> session.save(carMake));
    }

    public void fillModel(Model model) {
        this.tx(session -> session.save(model));
    }

    public void fillBodyType(BodyType bodyType) {
        this.tx(session -> session.save(bodyType));
    }

    public void fillEngineType(EngineType engineType) {
        this.tx(session -> session.save(engineType));
    }

    public void fillGearBoxType(GearboxType gearboxType) {
        this.tx(session -> session.save(gearboxType));
    }

    public void fillPicture(Picture picture) {
        this.tx(session -> session.save(picture));
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