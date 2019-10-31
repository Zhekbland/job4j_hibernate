package ru.job4j.models.carmodels;

public class CarMake {

    private int id;

    private String name;

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
