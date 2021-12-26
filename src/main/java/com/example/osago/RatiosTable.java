package com.example.osago;
public class RatiosTable {
    String baseRate;
    String powerRatio;
    String countDriversRatio;
    String periodRatio;
    String experienceRatio;
    String safeDriveRatio;
    String regionRatio;
    String totalPrice;
    //класс для создания объектов - таблицы коэффициентов
    public RatiosTable(String baseRate, String safeDriveRatio, String experienceRatio,String regionRatio, String powerRatio, String countDriversRatio, String periodRatio, String totalPrice) {
        this.baseRate = baseRate;
        this.powerRatio = powerRatio;
        this.countDriversRatio = countDriversRatio;
        this.periodRatio = periodRatio;
        this.experienceRatio = experienceRatio;
        this.safeDriveRatio = safeDriveRatio;
        this.regionRatio = regionRatio;
        this.totalPrice = totalPrice;
    }
    public String getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(String baseRate) {
        this.baseRate = baseRate;
    }

    public String getPowerRatio() {
        return powerRatio;
    }

    public void setPowerRatio(String powerRatio) {
        this.powerRatio = powerRatio;
    }

    public String getCountDriversRatio() {
        return countDriversRatio;
    }

    public void setCountDriversRatio(String countDriversRatio) {
        this.countDriversRatio = countDriversRatio;
    }

    public String getPeriodRatio() {
        return periodRatio;
    }

    public void setPeriodRatio(String periodRatio) {
        this.periodRatio = periodRatio;
    }

    public String getExperienceRatio() {
        return experienceRatio;
    }

    public void setExperienceRatio(String experienceRatio) {
        this.experienceRatio = experienceRatio;
    }

    public String getSafeDriveRatio() {
        return safeDriveRatio;
    }

    public void setSafeDriveRatio(String safeDriveRatio) {
        this.safeDriveRatio = safeDriveRatio;
    }

    public String getRegionRatio() {
        return regionRatio;
    }

    public void setRegionRatio(String regionRatio) {
        this.regionRatio = regionRatio;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

}
