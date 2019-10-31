package ru.job4j.persistence;

import org.junit.Test;
import org.junit.Before;
import ru.job4j.models.Car;
import ru.job4j.models.User;
import ru.job4j.models.carmodels.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AdvertStockDBTest {

    private final CarModelDB carModelDB = CarModelDB.getInstance();
    private final UserDB userDB = UserDB.getInstance();
    private final AdvertStockDB advertStockDB = AdvertStockDB.getInstance();

    @Before
    public void init() {
        CarMake toyota = new CarMake("Toyota");
        CarMake opel = new CarMake("Opel");
        carModelDB.fillCarMake(toyota);
        carModelDB.fillCarMake(opel);
        carModelDB.fillModel(new Model("Camry", toyota));
        carModelDB.fillModel(new Model("Astra H", opel));
        carModelDB.fillBodyType(new BodyType("Sedan"));
        carModelDB.fillBodyType(new BodyType("Hatchback"));
        carModelDB.fillEngineType(new EngineType("Petrol"));
        carModelDB.fillEngineType(new EngineType("Diesel"));
        carModelDB.fillGearBoxType(new GearboxType("Robot"));
        carModelDB.fillGearBoxType(new GearboxType("Automatic"));
        carModelDB.fillPicture(new Picture(new byte[0]));
        carModelDB.fillPicture(new Picture(new byte[0]));
    }

    @Test
    public void whenWeCreateAndUpdateCar() {
        User user = new User("Sam", "Samov", "test4@gmail.com", "999", "04");
        this.userDB.add(user);
        Car car = new Car();
        car.setCarMileage(1_000);
        car.setYear(2018);
        car.setPrice(1_900_000);
        car.setUser(user);
        car.setCarMake(new CarMake(2));
        car.setModel(new Model(2));
        car.setBodyType(new BodyType(2));
        car.setEngineType(new EngineType(1));
        car.setGearboxType(new GearboxType(2));
        car.setPicture(new Picture(2));
        this.advertStockDB.add(car);
        car.setPrice(2_000_000);
        this.advertStockDB.update(car);
        Car result = this.advertStockDB.getAll().get(1);
        assertThat(result.getPrice(), is(2_000_000));
        this.advertStockDB.delete(car);
        assertThat(this.advertStockDB.getAll().size(), is(0));
    }
}