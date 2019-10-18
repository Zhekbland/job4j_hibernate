package ru.job4j.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TableTest is testing UserType object creating.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 18.10.2019.
 */
public class TableTest {

    private SessionFactory factory;
    private final IPAddress ipAddress = new IPAddress("192.168.0.1", "8080");

    @Before
    public void initAndCreateAndFillTable() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        this.factory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        Computer computer = new Computer(this.ipAddress);
        Session session = this.factory.openSession();
        try {
            session.beginTransaction();
            session.save(computer);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenWedGetUserType() {
        Session session = this.factory.openSession();
        Computer result = new Computer();
        try {
            session.beginTransaction();
            result = session.get(Computer.class, 1);
            session.evict(result);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        assertThat(result.getIpAddress(), is(this.ipAddress));
    }
}

