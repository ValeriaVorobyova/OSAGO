package com.example.osago;

public class InsuranceCase {

    private String driverLastName;
    private String driverFirstName;
    private String driverPatronymic;
    private String driverPassport;
    private Double payment;
    private String caseDate;
    private String caseDescription;
    private String carNumber;
    //класс для создания объектов - страховых случаев. В БД будут записываться данные, полученные из геттеров
    public InsuranceCase(String driverLastName, String driverFirstName, String driverPatronymic, String driverPassport, Double payment, String caseDate, String carNumber, String caseDescription) {
        this.driverLastName = driverLastName;
        this.driverFirstName = driverFirstName;
        this.driverPatronymic = driverPatronymic;
        this.driverPassport = driverPassport;
        this.payment = payment;
        this.caseDate = caseDate;
        this.carNumber = carNumber;
        this.caseDescription = caseDescription;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverPatronymic() {
        return driverPatronymic;
    }

    public void setDriverPatronymic(String driverPatronymic) {
        this.driverPatronymic = driverPatronymic;
    }

    public String getDriverPassport() {
        return driverPassport;
    }

    public void setDriverPassport(String driverPassport) {
        this.driverPassport = driverPassport;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }
}
