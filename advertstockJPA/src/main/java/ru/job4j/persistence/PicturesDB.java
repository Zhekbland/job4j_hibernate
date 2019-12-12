package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.models.carmodels.Picture;

import java.util.Map;
import java.util.function.Function;

public class PicturesDB implements IStock<Picture> {

    private static final PicturesDB INSTANCE = new PicturesDB();
    private final SessionFactory sessionFactory = DB.getInstance().getSessionFactory();;

    public PicturesDB() {
    }

    public static PicturesDB getInstance() {
        return INSTANCE;
    }

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

    @Override
    public void add(Picture picture) {
        this.tx(session -> session.save(picture));
    }

    @Override
    public void update(Picture picture) {
        this.tx(session -> {
            session.update(picture);
            return null;
        });
    }

    @Override
    public void delete(Picture picture) {
        this.tx(session -> {
            session.delete(picture);
            return null;
        });
    }

    @Override
    public Map<Integer, Picture> getAll() {
        return null;
    }

    @Override
    public Picture getById(int id) {
        return this.tx(session -> session.get(Picture.class, id));
    }

    @Override
    public Picture isCredentional(String param1, String param2) {
        return null;
    }
}
