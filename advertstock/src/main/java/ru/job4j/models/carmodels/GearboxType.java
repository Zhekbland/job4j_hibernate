package ru.job4j.models.carmodels;

public class GearboxType {

    private int id;

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
