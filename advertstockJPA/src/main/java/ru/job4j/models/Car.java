package ru.job4j.models;

import ru.job4j.models.carmodels.*;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(precision = 6, scale = 0)
    private int carMileage;

    @Column(precision = 4)
    private int year;

    @Column(precision = 8, scale = 0)
    private int price;

    @Column
    private boolean onSale = true;

    @ManyToOne()
    private User user;

    @ManyToOne
    private CarMake carMake;

    @ManyToOne
    private Model model;

    @ManyToOne
    private BodyType bodyType;

    @ManyToOne
    private EngineType engineType;

    @ManyToOne
    private GearboxType gearboxType;

    @OneToOne
    private Picture picture;


    public Car() {
    }

    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarMake getCarMake() {
        return carMake;
    }

    public void setCarMake(CarMake carMake) {
        this.carMake = carMake;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public GearboxType getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(GearboxType gearboxType) {
        this.gearboxType = gearboxType;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && carMileage == car.carMileage && year == car.year && price == car.price
                && onSale == car.onSale && Objects.equals(carMake, car.carMake) && Objects.equals(model, car.model)
                && Objects.equals(bodyType, car.bodyType) && Objects.equals(engineType, car.engineType)
                && Objects.equals(gearboxType, car.gearboxType) && Objects.equals(picture, car.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carMileage, year, price, onSale, carMake, model, bodyType, engineType, gearboxType, picture);
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", carMileage=" + carMileage + ", year=" + year + ", price=" + price
                + ", onSale=" + onSale + ", carMake=" + carMake + ", model=" + model + ", bodyType=" + bodyType
                + ", engineType=" + engineType + ", gearboxType=" + gearboxType + ", picture=" + picture + '}';
    }
}