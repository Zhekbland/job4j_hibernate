package ru.job4j.models.carmodels;

import javax.persistence.*;

@Entity
@Table(name = "enginetypes")
public class EngineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    public EngineType() {
    }

    public EngineType(int id) {
        this.id = id;
    }

    public EngineType(String name) {
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
        return "EngineType{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
