package ru.job4j.models;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import javax.persistence.*;

/**
 * Class POJO for Hibernate's database creating.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 18.10.2019.
 */
@Entity
public class Computer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Type(type = "ru.job4j.models.IPAddressType")
    @Columns(columns = {
            @Column(name = "ip_adress"),
            @Column(name = "port")
    })
    private IPAddress ipAddress;

    public Computer() {

    }

    public Computer(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
}