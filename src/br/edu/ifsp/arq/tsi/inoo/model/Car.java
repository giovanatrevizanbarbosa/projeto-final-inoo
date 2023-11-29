package br.edu.ifsp.arq.tsi.inoo.model;

public class Car {
    private int code;
    private String model;
    private String brand;
    private int year;
    private String licensePlate;
    private int quantityDoors;
    private boolean hasAirConditioning;
    private double dailyRate;
    private boolean status;
    
    public Car(int code, String model, String brand, int year, String licensePlate, int quantityDoors,
            boolean hasAirConditioning, double dailyRate) {
        this.code = code;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.licensePlate = licensePlate;
        this.quantityDoors = quantityDoors;
        this.hasAirConditioning = hasAirConditioning;
        this.dailyRate = dailyRate;
        this.status = true;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getQuantityDoors() {
        return quantityDoors;
    }

    public void setQuantityDoors(int quantityDoors) {
        this.quantityDoors = quantityDoors;
    }

    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}