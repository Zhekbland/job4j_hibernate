package ru.job4j.models.carmodels;

import javax.persistence.*;

@Entity
@Table(name = "gearboxes")
public class GearboxType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    public GearboxType() {
    }

    public GearboxType(int id) {
        this.id = id;
    }

    public GearboxType(String name) {
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
        return "GearboxType{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
