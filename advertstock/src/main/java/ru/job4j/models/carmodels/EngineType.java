package ru.job4j.models.carmodels;

public class EngineType {

    private int id;

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
