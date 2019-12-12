package ru.job4j.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Car;
import ru.job4j.persistence.AdvertCarDB;
import ru.job4j.persistence.IStock;

import java.util.Map;

/**
 * Class ValidateServiceAdvertCar.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 07.12.2019.
 */
public class ValidateServiceAdvertCar implements Validate<Car> {

    private static final Logger LOG = LogManager.getLogger(ValidateServiceAdvertCar.class.getName());
    private final IStock<Car> advertCarDB = AdvertCarDB.getInstance();
    private static final ValidateServiceAdvertCar INSTANCE = new ValidateServiceAdvertCar();

    public static ValidateServiceAdvertCar getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(Car car) {
        boolean result = true;
        try {
            this.advertCarDB.add(car);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(Car car) {
        boolean result = true;
        try {
            this.advertCarDB.delete(car);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public Map<Integer, Car> getAll() {
        return this.advertCarDB.getAll();
    }

    @Override
    public boolean update(Car car) {
        boolean result = true;
        try {
            this.advertCarDB.update(car);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public Car getById(int id) {
        return this.advertCarDB.getById(id);
    }

    @Override
    public Car isCredentioanal(String email, String password) {
        return new Car();
    }
}
