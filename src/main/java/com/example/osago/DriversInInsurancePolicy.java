package com.example.osago;
public class DriversInInsurancePolicy {
    String lastName, firstName, patronymic, driverCardNumber;
    //класс для создания объектов - водителей, которые указаны в определенном страховом полюсе. В БД будут записываться данные, полученные из геттеров
    public DriversInInsurancePolicy(String lastName, String firstName, String patronymic, String driverCardNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.driverCardNumber = driverCardNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDriverCardNumber() {
        return driverCardNumber;
    }

    public void setDriverCardNumber(String driverCardNumber) {
        this.driverCardNumber = driverCardNumber;
    }
}

