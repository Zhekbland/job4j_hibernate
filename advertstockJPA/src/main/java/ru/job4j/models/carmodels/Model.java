package ru.job4j.models.carmodels;


import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "carmake_id")
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
