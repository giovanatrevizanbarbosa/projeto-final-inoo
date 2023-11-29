package br.edu.ifsp.arq.tsi.inoo.model;

import java.time.LocalDate;

public class Rental {
    private int number;
    private LocalDate dayRental;
    private int numberDiaries;
    private LocalDate maxDate;
    private LocalDate returnDate;
    private Client client;
    private Car car;

    public Rental(int number, LocalDate dayRental, int numberDiaries, LocalDate maxDate, LocalDate returnDate,
            Client client, Car car) {
        this.number = number;
        this.dayRental = LocalDate.now();
        this.numberDiaries = numberDiaries;
        this.maxDate = calcMaxDate();
        this.returnDate = null;
        this.client = client;
        this.car = car;
    }

    private LocalDate calcMaxDate() {
        LocalDate maxDate = this.dayRental.plusDays(numberDiaries);
        return maxDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getDayRental() {
        return dayRental;
    }

    public void setDayRental(LocalDate dayRental) {
        this.dayRental = dayRental;
    }

    public int getNumberDiaries() {
        return numberDiaries;
    }

    public void setNumberDiaries(int numberDiaries) {
        this.numberDiaries = numberDiaries;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
