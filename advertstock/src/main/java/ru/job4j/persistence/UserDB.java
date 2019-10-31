package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.models.User;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserDB implements IStock<User> {

    private static final UserDB INSTANCE = new UserDB();
    private final SessionFactory sessionFactory;

    public UserDB() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        this.sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
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

//    @Override
//    public void update(User user) {
//        final Session session = this.sessionFactory.openSession();
//        final Transaction transaction = session.beginTransaction();
//        try {
////            User dbUser = session.get(User.class, user.getId());
////            dbUser = user;
//            session.update(user);
//            transaction.commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//            throw e;
//        } finally {
//            session.close();
//        }
//    }

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
//        return (List<User>) this.tx(session -> session.createQuery("from User ").list());
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