package com.example.osago;

public class Driver {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String birthDate;
    private String passport;
    private String passportDate;
    private String driverCardNumber;
    private String driverExperience;
    private String driverRating;
    //класс для создания объектов - водителей. В БД будут записываться данные, полученные из геттеров
    public Driver(String firstName, String lastName, String patronymic, String birthDate, String passport, String passportDate, String driverCardNumber, String driverExperience, String driverRating) {


        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.passport = passport;
        this.passportDate = passportDate;
        this.driverCardNumber = driverCardNumber;
        this.driverExperience = driverExperience;
        this.driverRating = driverRating;
    }

    public Driver(){}

    public String getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(String driverRating) {
        this.driverRating = driverRating;
    }

    public String getDriverExperience() {
        return driverExperience;
    }

    public void setDriverExperience(String driverExperience) {
        this.driverExperience = driverExperience;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(String passportDate) {
        this.passportDate = passportDate;
    }

    public String getDriverCardNumber() {
        return driverCardNumber;
    }

    public void setDriverCardNumber(String driverCardNumber) {
        this.driverCardNumber = driverCardNumber;
    }
}
