package ru.job4j.services;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.models.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Class HibernateRun does a lot of actions with DB.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 30.09.2019.
 */
public class HibernateRun {

    /**
     * Add user by name via hibernate
     * @param factory class for creation session.
     * @param name for user creating.
     */
    public void addUser(SessionFactory factory, String name) {
        User user = new User();
        user.setName(name);
        user.setExpired(getDate());
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove user by id via hibernate.
     * @param factory class for creation session.
     * @param id of user.
     */
    public void removeUser(SessionFactory factory, int id) {
        User user = new User();
        user.setId(id);
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update user by id, name via hibernate.
     * @param factory class for creation session.
     * @param id of user.
     * @param name - new name of user.
     */
    public void updateUser(SessionFactory factory, int id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setExpired(getDate());
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find user by id.
     * @param factory class for creation session.
     * @param id for searching.
     * @return user model.
     */
    public User findById(SessionFactory factory, int id) {
        User user = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User where id = :id");
            query.setParameter("id", id);
            user = query.list().size() != 0 ? (User) query.iterate().next() : null;
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Find user by name.
     * @param factory class for creation session.
     * @param name for searching.
     * @return user model.
     */
    public User findByName(SessionFactory factory, String name) {
        User user = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User where name = :name");
            query.setParameter("name", name);
            user = query.list().size() != 0 ? (User) query.iterate().next() : null;
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Find all user in DB.
     * @param factory class for creation session.
     * @return list of users.
     */
    public List<User> findAll(SessionFactory factory) {
        List<User> userList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            userList = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Remove all users in DB.
     * @param factory class for creation session.
     */
    public void removeAll(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create real time date and sum 1 year.
     * @return class for creation session.
     */
    private Calendar getDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        return calendar;
    }
}