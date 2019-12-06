package ru.job4j.models.carmodels;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carmakes")
public class CarMake {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @OneToMany
    private List<Model> model;

    public CarMake() {

    }

    public CarMake(int id) {
        this.id = id;
    }

    public CarMake(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarMake{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
