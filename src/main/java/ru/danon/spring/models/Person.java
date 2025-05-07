package ru.danon.spring.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message="ФИО не должно быть пустым")
    @Size(min=2, max=200, message = "Имя должно быть от 2 до 200 символов длиной")
    private String fullName;
    @NotNull(message = "Год рождения не должен быть пустым")
    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int yearBirth;

    public Person() {}
    public Person(String fullName, int yearBirth) {
        this.fullName = fullName;
        this.yearBirth = yearBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
