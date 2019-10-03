package ru.job4j.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class HibernateRunTest is testing.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 30.09.2019.
 */
public class HibernateRunTest {

    private SessionFactory factory;
    private final HibernateRun hibernate = new HibernateRun();

    @Before
    public void init() {
        this.factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    @After
    public void close() {
        this.hibernate.removeAll(this.factory);
        this.factory.close();
    }

    @Test
    public void whenWeAddUserAndGetById() {
        String name = "Inna";
        hibernate.addUser(this.factory, name);
        int userId = hibernate.findByName(this.factory, name).getId();
        User user = hibernate.findById(this.factory, userId);
        assertThat(user.getId(), is(userId));
    }

    @Test
    public void whenWeAddUserAndGetByName() {
        String name = "Max";
        hibernate.addUser(this.factory, name);
        User user = hibernate.findByName(this.factory, name);
        assertThat(user.getName(), is(name));
    }

    @Test
    public void whenWeUpdateUserById() {
        String name = "Zhenya";
        hibernate.addUser(this.factory, name);
        int userId = hibernate.findByName(this.factory, name).getId();
        String newName = "Evgeniy";
        hibernate.updateUser(this.factory, userId, newName);
        User user = hibernate.findByName(this.factory, newName);
        assertThat(user.getName(), is(newName));
    }

    @Test
    public void whenWeDeleteUser() {
        String name = "Bob";
        hibernate.addUser(this.factory, name);
        int userId = hibernate.findByName(this.factory, name).getId();
        hibernate.removeUser(this.factory, userId);
        User user = hibernate.findByName(this.factory, name);
        assertNull(user);
    }
}