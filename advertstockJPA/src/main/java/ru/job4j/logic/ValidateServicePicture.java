package ru.job4j.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.carmodels.Picture;
import ru.job4j.persistence.IStock;
import ru.job4j.persistence.PicturesDB;

import java.util.Map;

/**
 * Class ValidateServicePicture.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 07.12.2019.
 */
public class ValidateServicePicture implements Validate<Picture> {

    private final IStock<Picture> picturesDB = PicturesDB.getInstance();
    private static final ValidateServicePicture INSTANCE = new ValidateServicePicture();
    private static final Logger LOG = LogManager.getLogger(ValidateServicePicture.class.getName());

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(Picture picture) {
        boolean result = true;
        try {
            this.picturesDB.add(picture);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(Picture picture) {
        boolean result = true;
        try {
            this.picturesDB.delete(picture);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public Map<Integer, Picture> getAll() {
        return null;
    }

    @Override
    public boolean update(Picture picture) {
        boolean result = true;
        try {
            this.picturesDB.update(picture);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public Picture getById(int id) {
        return this.picturesDB.getById(id);
    }

    @Override
    public Picture isCredentioanal(String email, String password) {
        return null;
    }
}
