package ru.job4j.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.User;
import ru.job4j.persistence.IStock;
import ru.job4j.persistence.UserDB;

import java.util.Map;

/**
 * Class ValidateServiceUser.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 07.12.2019.
 */
public class ValidateServiceUser implements Validate<User> {

    private static final ValidateServiceUser INSTANCE = new ValidateServiceUser();
    private static final Logger LOG = LogManager.getLogger(ValidateServiceUser.class.getName());
    private final IStock<User> userDB = UserDB.getInstance();


    public static ValidateServiceUser getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        boolean result = true;
        try {
            this.userDB.add(user);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = true;
        try {
            this.userDB.delete(user);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public Map<Integer, User> getAll() {
        return this.userDB.getAll();
    }

    @Override
    public boolean update(User user) {
        boolean result = true;
        try {
            this.userDB.update(user);
        } catch (Exception e) {
            LOG.error(e);
            result = false;
        }
        return result;
    }

    @Override
    public User getById(int id) {
        return this.userDB.getById(id);
    }

    @Override
    public User isCredentioanal(String email, String password) {
        User user = null;
        try {
            user = this.userDB.isCredentional(email, password);
        } catch (Exception e) {
            LOG.error(e);
        }
        return user;
    }


}
