package com.yourcomapany.model;

/**
 * Created by Alexander on 10.09.2016.
 */
public class Book {

    private int id;
    private String name;
    private String author;
    private String desc;
    private byte[] icon;

    public Book(){}

    public Book(int id, String name, String author, String desc, byte[] icon) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.desc = desc;
        this.icon = icon;
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Book(String name, String author, String desc) {
        this.name = name;
        this.author = author;
        this.desc = desc;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return String.format("║ Книга №%3d\t║ %20s\t║ автор: %10s\t║ %30s\t║ ", id, name, author, desc);
    }
}
