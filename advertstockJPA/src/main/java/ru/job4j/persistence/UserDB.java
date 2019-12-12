package ru.job4j.persistence;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.models.User;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserDB implements IStock<User> {

    private final static Logger LOG = LogManager.getLogger(UserDB.class.getName());
    private static final UserDB INSTANCE = new UserDB();
    private final SessionFactory sessionFactory = DB.getInstance().getSessionFactory();;

    public UserDB() {
    }

    public static UserDB getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        this.tx(session -> {
            session.save(user);
            return null;
        });
    }

    @Override
    public void update(User user) {
        this.tx(session -> {
            session.update(user);
            return null;
        });
    }

    @Override
    public void delete(User user) {
        this.tx(session -> {
            session.delete(user);
            return null;
        });
    }

    @Override
    public Map<Integer, User> getAll() {
        return (Map<Integer, User>) this.tx(session -> session.createQuery("from User").list()
                .stream()
                .collect(Collectors.toMap(User::getId, user -> user)));
    }

    @Override
    public User getById(int id) {
        return this.tx(session -> session.get(User.class, id, LockMode.PESSIMISTIC_WRITE));
    }

    @Override
    public User isCredentional(String email, String password) {
        return this.tx(session -> {
            Query query = session.createQuery("from User where email = :email and password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            return (User) query.getSingleResult();
        });
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