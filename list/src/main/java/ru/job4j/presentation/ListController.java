package ru.job4j.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet ListController create JSON from BD's items.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 05.10.2019.
 */
public class ListController extends HttpServlet {

    private final Validate service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(mapper.writeValueAsString(this.service.getAll().values()));
        }
    }
}
