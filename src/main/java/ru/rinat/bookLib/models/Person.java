package ru.rinat.bookLib.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 8, max = 50, message = "Name should be between 8 and 50 characters")
    private String fullName;

    @Min(value = 1900, message = "Birth year should be greater than 1900")
    private int birthYear;

    public Person() {
    }

    public Person(int personId, String fullName, int birthYear) {
        this.personId = personId;
        this.fullName = fullName;
        this.birthYear = birthYear;

    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
