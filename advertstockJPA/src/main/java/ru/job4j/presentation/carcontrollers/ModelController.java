package ru.job4j.presentation.carcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.persistence.PartsCarDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ModelController extends HttpServlet {

    private final PartsCarDB partsCarDB = PartsCarDB.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(ModelController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = req.getReader();
                PrintWriter writer = resp.getWriter()) {
            String model = mapper.readValue(reader.readLine(), String.class);
            writer.println(mapper.writeValueAsString(this.partsCarDB.getModels(Integer.parseInt(model)).values()));
        } catch (Throwable throwable) {
            LOGGER.error(throwable);
        }
    }
}
