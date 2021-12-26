package com.example.osago;
public class Insurer {
    private int idcar;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String birthDate;
    private String passport;
    private String passportDate;
    private String passportDepartment;
    //класс для создания объектов - страхователей. В БД будут записываться данные, полученные из геттеров
    public Insurer(int idcar, String firstName, String lastName, String patronymic, String birthDate, String passport, String passportDate, String passportDepartment) {
        this.idcar = idcar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.passport = passport;
        this.passportDate = passportDate;
        this.passportDepartment = passportDepartment;
    }

    public int getIdcar() {
        return idcar;
    }

    public void setIdcar(int idcar) {
        this.idcar = idcar;
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

    public String getPassportDepartment() {
        return passportDepartment;
    }

    public void setPassportDepartment(String passportDepartment) {
        this.passportDepartment = passportDepartment;
    }
}
