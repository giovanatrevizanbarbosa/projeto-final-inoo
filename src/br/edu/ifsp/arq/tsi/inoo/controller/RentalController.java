package br.edu.ifsp.arq.tsi.inoo.controller;

import br.edu.ifsp.arq.tsi.inoo.model.*;

import java.time.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class RentalController {
    private static RentalController instance;

    private ArrayList<Rental> rentals;
    private int nextCode;

    DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private RentalController() {
        rentals = new ArrayList<>();
        nextCode = 1;
    }

    public static synchronized RentalController getInstance() {
        if (instance == null) {
            instance = new RentalController();
        }
        return instance;
    }

    public boolean rental(Rental rental) {
        if (rental != null) {
            rental.setNumber(nextCode++);
            rentals.add(rental);
            return true;
        }
        return false;
    }

    public boolean devolution(Rental rental) {
        if (rental != null) {
            // valor da diária * numero de diarias efetivas
            Car car = rental.getCar();
            rental.setReturnDate(LocalDate.now());
            car.setStatus(true);
            System.out.println("Valor a ser pago: " + calcTotalValue(rental, car));
            return true;
        }
        return false;
    }

    public double calcTotalValue(Rental rental, Car car) {
        if (rental != null) {
            Period diff = Period.between(rental.getDayRental(), rental.getReturnDate());
            return car.getDailyRate() * diff.getDays();
        }
        return 0;
    }

    public String generateReport() {
        String msg = "RELATÓRIO DE ALUGUÉIS\n";
        msg += "---------------------------------------------------\n";
        for (Rental r : rentals) {
            msg += "Aluguel: " + r.getNumber() + "\n";
            msg += "Data da realização: " + r.getDayRental().format(dataTimeFormatter) + "\n";
            msg += "Número de diárias: " + r.getNumberDiaries() + "\n";
            msg += "Data máxima para devolução: " + r.getMaxDate().format(dataTimeFormatter) + "\n";
            msg += "Data de devolução: " + r.getReturnDate().format(dataTimeFormatter) + "\n";
            msg += "Cliente: " + r.getClient().getName() + "\n";
            msg += "Carro escolhido: " + r.getCar().getModel() + "\n";
            msg += "---------------------------------------------------\n";
        }
        return msg;
    }

    public ArrayList<Rental> getRentals() {
        return rentals;
    }
}