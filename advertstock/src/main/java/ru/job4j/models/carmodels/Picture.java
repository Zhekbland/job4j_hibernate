package ru.job4j.models.carmodels;

import java.util.Arrays;

public class Picture {

    private int id;

    private byte[] image;

    public Picture() {
    }

    public Picture(int id) {
        this.id = id;
    }

    public Picture(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Picture{" + "id=" + id + ", image=" + Arrays.toString(image) + '}';
    }
}
