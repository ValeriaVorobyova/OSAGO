package com.example.osago;

public class Car {
    private String year;
    private String brand;
    private String model;
    private String power;
    private String region;
    private String drivers;
    private String VIN;
    private String period;
    private String number;

    //класс для создания объектов - автомобилей. В БД будут записываться данные, полученные из геттеров
    public Car(String year, String brand, String model, String power, String region, String drivers, String VIN, String period,String number) {
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.region = region;
        this.drivers = drivers;
        this.VIN = VIN;
        this.period = period;
        this.number = number;
    }

    public Car() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDrivers() {
        return drivers;
    }

    public void setDrivers(String drivers) {
        this.drivers = drivers;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }
}
