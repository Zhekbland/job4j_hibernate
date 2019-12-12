package ru.job4j.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateServiceAdvertCar;
import ru.job4j.logic.ValidateServiceUser;
import ru.job4j.models.Car;
import ru.job4j.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdvertsController extends HttpServlet {

    private final Validate<Car> serviceCar = ValidateServiceAdvertCar.getInstance();
    private final Validate<User> serviceUser = ValidateServiceUser.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(AdvertsController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", serviceCar.getAll().values());
        req.setAttribute("users", this.serviceUser.getAll().values());
        req.getRequestDispatcher("WEB-INF/views/AdvertsView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}
