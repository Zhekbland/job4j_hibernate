package ru.job4j.models;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Class POJO for Hibernate's database creating.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 18.10.2019.
 */
public class User {

    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String phone;

    private Map<Long, Car> categories = new TreeMap<>();

    public User() {

    }

    public User(int id) {
        this.id = id;
    }

    public User(String name, String surname, String email, String password, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map<Long, Car> getCategories() {
        return categories;
    }

    public void setCategories(Map<Long, Car> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(surname, user.surname)
                && Objects.equals(email, user.email) && Objects.equals(password, user.password)
                && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, phone);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", email='" + email
                + '\'' + ", password='" + password + '\'' + ", phone='" + phone + '\'' + ", categories="
                + categories + '}';
    }
}