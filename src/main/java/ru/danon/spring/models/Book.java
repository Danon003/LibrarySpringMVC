package ru.danon.spring.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Book {
    private int id;
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 200, message = "Название должно быть не менее 2 и не более 200 символов")
    private String name;
    @NotEmpty(message = "ФИО автора не должно быть пустым")
    @Size(min = 2, max = 200, message = "ФИО автора должно быть не менее 2 и не более 200 символов")
    private String author;
    @NotNull(message = "Год выпуска книги не должен быть пустым")
    @Min(value = 1500, message = "Год должен быть больше, чем 1500")
    private int year;

    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
