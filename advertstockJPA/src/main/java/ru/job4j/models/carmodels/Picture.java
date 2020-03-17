package ru.job4j.models.carmodels;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false, unique = true)
    private String fileName;

    @Lob()
    private byte[] image;

    public Picture() {
    }

    public Picture(String filePath, String fileName, byte[] image) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.image = image;
    }

    public Picture(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public Picture(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Picture{" + "id=" + id + ", filePath='" + filePath + '\'' + ", fileName='" + fileName + '\'' + '}';
    }
}
