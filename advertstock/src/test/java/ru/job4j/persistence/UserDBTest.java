package ru.job4j.persistence;

import org.junit.Test;
import ru.job4j.models.User;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserDBTest {

    private final UserDB userDB = UserDB.getInstance();

    @org.junit.Test
    public void whenWeAddUserAndGetIt() {
        User user = new User("Alex", "Alexov", "test1@gmail.com", "123", "02");
        this.userDB.add(user);
        User result = this.userDB.getAll().get(user.getId());
        assertThat(result, is(user));
    }

    @org.junit.Test
    public void whenWeAddAndDeleteAll() {
        User user = new User("Max", "Maxov", "test2@gmail.com", "321", "01");
        this.userDB.add(user);
        Map<Integer, User> allUsers = this.userDB.getAll();
        for (Map.Entry<Integer, User> entry : allUsers.entrySet()) {
            this.userDB.delete(entry.getValue());
        }
        assertThat(0, is(this.userDB.getAll().size()));
    }

    @Test
    public void whenWeUpdateOurUser() {
        User user = new User("Tom", "Tomov", "test3@gmail.com", "4123", "03");
        this.userDB.add(user);
        user.setName("John");
        this.userDB.update(user);
        assertThat(this.userDB.getAll().get(user.getId()).getName(), is("John"));
    }

}