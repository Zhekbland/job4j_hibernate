package ru.job4j.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.logic.Validate;
import ru.job4j.logic.ValidateService;
import ru.job4j.models.Item;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Servlet CreateOrUpdateController create or update items into BD.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 05.10.2019.
 */
public class CreateOrUpdateController extends HttpServlet {

    private final Validate service = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            if (reader != null) {
                sb.append(reader.readLine());
            }
        }
        Item item = mapper.readValue(sb.toString(), Item.class);
        this.service.createOrUpdate(item);
    }
}
