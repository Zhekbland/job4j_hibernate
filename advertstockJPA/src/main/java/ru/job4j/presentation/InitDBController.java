package ru.job4j.presentation;

import ru.job4j.models.Car;
import ru.job4j.models.User;
import ru.job4j.models.carmodels.*;
import ru.job4j.persistence.AdvertCarDB;
import ru.job4j.persistence.CarModelDB;
import ru.job4j.persistence.UserDB;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class InitDBController extends HttpServlet {

    private final UserDB userDB = UserDB.getInstance();
    private final CarModelDB carModelDB = CarModelDB.getInstance();
    private final AdvertCarDB advertCarDB = AdvertCarDB.getInstance();

    @Override
    public void init() {
        createImageFolder();
        createUsers();
        createCarsDetails();
        createUsersCars();
    }

    private void createUsers() {
        this.userDB.add(new User("Max", "Maxov", "max@gmail.com", "3214", "01"));
        this.userDB.add(new User("Semen", "Semenov", "semen@gmail.com", "5566", "02"));
        this.userDB.add(new User("John", "Johnson", "john@gmail.com", "3994", "03"));
    }

    private void createCarsDetails() {
        CarMake toyota = new CarMake("Toyota");
        CarMake opel = new CarMake("Opel");
        CarMake ford = new CarMake("Ford");
        carModelDB.fillCarMake(toyota);
        carModelDB.fillCarMake(opel);
        carModelDB.fillCarMake(ford);
        carModelDB.fillModel(new Model("Camry", toyota));
        carModelDB.fillModel(new Model("LandCruiser", toyota));
        carModelDB.fillModel(new Model("Vista", toyota));
        carModelDB.fillModel(new Model("Astra J", opel));
        carModelDB.fillModel(new Model("Insignia", opel));
        carModelDB.fillModel(new Model("Corsa E", opel));
        carModelDB.fillModel(new Model("Fiesta", ford));
        carModelDB.fillModel(new Model("Focus II", ford));
        carModelDB.fillModel(new Model("Mondeo", ford));
        carModelDB.fillBodyType(new BodyType("Sedan"));
        carModelDB.fillBodyType(new BodyType("Hatchback"));
        carModelDB.fillBodyType(new BodyType("Universal"));
        carModelDB.fillBodyType(new BodyType("Jeep"));
        carModelDB.fillEngineType(new EngineType("Petrol"));
        carModelDB.fillEngineType(new EngineType("Diesel"));
        carModelDB.fillEngineType(new EngineType("Hybrid"));
        carModelDB.fillGearBoxType(new GearboxType("Robot"));
        carModelDB.fillGearBoxType(new GearboxType("Automatic"));
        carModelDB.fillGearBoxType(new GearboxType("Mechanic"));
        String imgRealPath = getServletContext().getAttribute("imgRealPath").toString();
        carModelDB.fillPicture(new Picture(imgRealPath, "toyota.jpg",
                savePictureInDB(imgRealPath, "toyota.jpg")));
        carModelDB.fillPicture(new Picture(imgRealPath, "opel.jpg",
                savePictureInDB(imgRealPath, "opel.jpg")));
        carModelDB.fillPicture(new Picture(imgRealPath, "ford.jpg",
                savePictureInDB(imgRealPath, "ford.jpg")));
    }

    private void createUsersCars() {
        Car car1 = new Car();
        car1.setCarMileage(1_000);
        car1.setYear(2018);
        car1.setPrice(1_900_000);
        car1.setUser(this.userDB.getById(1));
        car1.setCarMake(new CarMake(1));
        car1.setModel(new Model(1));
        car1.setBodyType(new BodyType(1));
        car1.setEngineType(new EngineType(1));
        car1.setGearboxType(new GearboxType(2));
        car1.setPicture(new Picture(1));
        this.advertCarDB.add(car1);

        Car car2 = new Car();
        car2.setCarMileage(80_000);
        car2.setYear(2013);
        car2.setPrice(900_000);
        car2.setUser(this.userDB.getById(2));
        car2.setCarMake(new CarMake(2));
        car2.setModel(new Model(4));
        car2.setBodyType(new BodyType(2));
        car2.setEngineType(new EngineType(1));
        car2.setGearboxType(new GearboxType(3));
        car2.setPicture(new Picture(2));
        this.advertCarDB.add(car2);

        Car car3 = new Car();
        car3.setCarMileage(250_000);
        car3.setYear(2008);
        car3.setPrice(300_000);
        car3.setUser(this.userDB.getById(2));
        car3.setCarMake(new CarMake(3));
        car3.setModel(new Model(8));
        car3.setBodyType(new BodyType(2));
        car3.setEngineType(new EngineType(1));
        car3.setGearboxType(new GearboxType(1));
        car3.setPicture(new Picture(3));
        this.advertCarDB.add(car3);
    }

    private void createImageFolder() {
        String realPath = getServletContext().getRealPath("/database/img/");
        String contextPath = getServletContext().getContextPath() + "/database/img/";
        getServletContext().setAttribute("imgContextPath", contextPath);
        getServletContext().setAttribute("imgRealPath", realPath);
    }

    private byte[] savePictureInDB(String resourcePic, String fileName) {
        BufferedImage example;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            example = ImageIO.read(new File(resourcePic + fileName));
            ImageIO.write(example, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
