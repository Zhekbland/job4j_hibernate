package ru.job4j.models.carmodels;

public class Model {

    private int id;

    private String name;

    private CarMake carMake;

    public Model() {

    }

    public Model(int id) {
        this.id = id;
    }

    public Model(String name, CarMake carMake) {
        this.name = name;
        this.carMake = carMake;
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

    public CarMake getCarMake() {
        return carMake;
    }

    public void setCarMake(CarMake carMake) {
        this.carMake = carMake;
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}