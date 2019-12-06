package ru.job4j.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.logic.ValidateServiceAdvertCar;
import ru.job4j.logic.ValidateServicePicture;
import ru.job4j.logic.ValidateServiceUser;
import ru.job4j.models.Car;
import ru.job4j.models.User;
import ru.job4j.models.carmodels.*;
import ru.job4j.persistence.PartsCarDB;
import ru.job4j.presentation.carcontrollers.ModelController;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;

@MultipartConfig
public class CreateAdvertController extends HttpServlet {

    private final ValidateServicePicture servicePicture = ValidateServicePicture.getInstance();
    private final ValidateServiceAdvertCar serviceAdvertCar = ValidateServiceAdvertCar.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(CreateAdvertController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("WEB-INF/views/CreateAdvertView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Picture picture = createPicture(req);
        this.servicePicture.add(picture);
        Car car = createCar(req, picture.getId());
        this.serviceAdvertCar.add(car);
        resp.sendRedirect(String.format("%s/adverts", req.getContextPath()));
    }

    private Picture createPicture(HttpServletRequest request) {
        Picture picture = new Picture();
        Part filePart = null;
        String imgRealPath = getServletContext().getAttribute("imgRealPath").toString();
        String pictureName = createUniquePictureName(request);
        try {
            filePart = request.getPart("fileUpload");
            byte[] bytesPicture = filePart.getInputStream().readAllBytes();
            picture.setImage(bytesPicture);
            picture.setFilePath(imgRealPath);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytesPicture));
            File newImage = new File(imgRealPath, pictureName + ".jpg");
            ImageIO.write(image, "JPEG", newImage);
            pictureName = newImage.getName();
            picture.setFileName(pictureName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return picture;
    }

    private String createUniquePictureName(HttpServletRequest request) {
        return String.valueOf((System.currentTimeMillis()
        + (int) request.getSession().getAttribute("userId")) / 2);
    }

    private Car createCar(HttpServletRequest request, int pictureId) {
        Car car = new Car();
        car.setCarMileage(Integer.parseInt(request.getParameter("carMileage")));
        car.setYear(Integer.parseInt(request.getParameter("year")));
        car.setPrice(Integer.parseInt(request.getParameter("price")));
        car.setUser(new User(Integer.parseInt(request.getParameter("user"))));
        car.setCarMake(new CarMake(Integer.parseInt(request.getParameter("mark"))));
        car.setModel(new Model(Integer.parseInt(request.getParameter("model"))));
        car.setBodyType(new BodyType(Integer.parseInt(request.getParameter("bodytype"))));
        car.setEngineType(new EngineType(Integer.parseInt(request.getParameter("enginetype"))));
        car.setGearboxType(new GearboxType(Integer.parseInt(request.getParameter("gearboxtype"))));
        car.setPicture(new Picture(pictureId));
        return car;
    }
}
