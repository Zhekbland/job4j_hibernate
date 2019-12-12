package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.models.carmodels.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PartsCarDB {

    private static final PartsCarDB INSTANCE = new PartsCarDB();
    private final SessionFactory sessionFactory = DB.getInstance().getSessionFactory();;

    public PartsCarDB() {
    }

    public static PartsCarDB getInstance() {
        return INSTANCE;
    }

    public Map<Integer, CarMake> getMarks() {
        return (Map<Integer, CarMake>) this.tx(session -> session.createQuery("from CarMake ").list())
                .stream()
                .collect(Collectors.toMap(CarMake::getId, carMake -> carMake));
    }

    public Map<Integer, Model> getModels(int carMakeId) {
        return this.tx(session -> {
            Query query = session.createQuery("from Model as m join fetch m.carMake where m.carMake.id = :carmake_id");
//            Query query = session.createQuery("from Model where carMake.id = :carmake_id");
            query.setParameter("carmake_id", carMakeId);

            return (Map<Integer, Model>) query.list().stream()
                    .collect(Collectors.toMap(Model::getId, model -> model));
        });
    }

    public Map<Integer, BodyType> getBodies() {
        return (Map<Integer, BodyType>) this.tx(session -> session.createQuery("from BodyType ").list()
                .stream()
                .collect(Collectors.toMap(BodyType::getId, bodyType -> bodyType)));
    }
    
    public Map<Integer, EngineType> getEngineType() {
        return (Map<Integer, EngineType>) this.tx(session -> session.createQuery("from EngineType ").list()
                .stream()
                .collect(Collectors.toMap(EngineType::getId, engineType -> engineType)));
    }

    public Map<Integer, GearboxType> getGearboxType() {
        return (Map<Integer, GearboxType>) this.tx(session -> session.createQuery("from GearboxType ").list()
                .stream()
                .collect(Collectors.toMap(GearboxType::getId, gearboxType -> gearboxType)));
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.sessionFactory.openSession();
        session.setDefaultReadOnly(true);
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
