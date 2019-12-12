package ru.job4j.presentation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateServiceAdvertCar;
import ru.job4j.models.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


public class UsersAdvertController extends HttpServlet {

    private final Validate<Car> validateServiceAdvertCar = ValidateServiceAdvertCar.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int activeUsersId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
        req.setAttribute("usersCars", this.validateServiceAdvertCar.getAll().values()
                .stream()
                .filter(car -> car.getUser().getId() == activeUsersId)
        .collect(Collectors.toList()));
        req.getRequestDispatcher("WEB-INF/views/MyAdvert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(req.getReader());
        boolean saleStatus = Boolean.valueOf(jsonNode.get("saleStatus").asText());
        Integer carId = Integer.parseInt(jsonNode.get("carId").asText());
        Car car = this.validateServiceAdvertCar.getById(carId);
        car.setOnSale(saleStatus);
        this.validateServiceAdvertCar.update(car);
        resp.sendRedirect(String.format("%s/adverts", req.getContextPath()));
    }
}
