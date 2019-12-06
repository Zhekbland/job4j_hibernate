package ru.job4j.presentation.carcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.persistence.PartsCarDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class BodyTypeController extends HttpServlet {
    private final PartsCarDB partsCarDB = PartsCarDB.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(BodyTypeController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        ObjectMapper mapper = new ObjectMapper();
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(mapper.writeValueAsString(this.partsCarDB.getBodies().values()));
        } catch (Throwable throwable) {
            LOGGER.error(throwable);
        }
    }
}
