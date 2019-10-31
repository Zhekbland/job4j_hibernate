package ru.job4j.models.carmodels;

public class BodyType {

    private int id;

    private String name;

    public BodyType() {
    }

    public BodyType(int id) {
        this.id = id;
    }

    public BodyType(String name) {
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
        return "BodyType{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
