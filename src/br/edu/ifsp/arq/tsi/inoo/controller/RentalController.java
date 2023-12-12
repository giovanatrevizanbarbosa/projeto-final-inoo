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
            Car car = rental.getCar();
            if(car.getStatus() == true){
                rental.setNumber(nextCode++);
                car.setStatus(false);
                rentals.add(rental);
                return true;
            }
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
        }else{
            return 0;
        }
    }

    public String getClientRentals(Client client) {
        String msg = "ALUGUÉIS DO CLIENTE " + client.getName() + "\n";
        msg += "---------------------------------------------------\n";
        for (Rental r : rentals) {
            if (r.getClient().equals(client)) {
                msg += "Aluguel: " + r.getNumber() + "\n";
                msg += "Data da realização: " + r.getDayRental().format(dataTimeFormatter) + "\n";
                msg += "Número de diárias: " + r.getNumberDiaries() + "\n";
                msg += "Data máxima para devolução: " + r.getMaxDate().format(dataTimeFormatter) + "\n";
                if(r.getReturnDate() != null){
                    msg += "Data de devolução: " + r.getReturnDate().format(dataTimeFormatter) + "\n";
                }else{
                    msg += "Data de devolução: Ainda não devolvido\n";
                }
                msg += "Carro escolhido: " + r.getCar().getModel() + "\n";
                msg += "---------------------------------------------------\n";
            }
        }
        return msg;
    }

    public String generateReport() {
        String msg = "RELATÓRIO DE ALUGUÉIS: " + getTotalRentals() + "\n";
        msg += "---------------------------------------------------\n";
        for (Rental r : rentals) {
            msg += "Aluguel: " + r.getNumber() + "\n";
            msg += "Data da realização: " + r.getDayRental().format(dataTimeFormatter) + "\n";
            msg += "Número de diárias: " + r.getNumberDiaries() + "\n";
            msg += "Data máxima para devolução: " + r.getMaxDate().format(dataTimeFormatter) + "\n";
            if (r.getReturnDate() != null) {
                msg += "Data de devolução: " + r.getReturnDate().format(dataTimeFormatter) + "\n";
            } else {
                msg += "Data de devolução: Ainda não devolvido\n";
            }
            msg += "Cliente: " + r.getClient().getName() + "\n";
            msg += "Carro escolhido: " + r.getCar().getModel() + "\n";
            msg += "---------------------------------------------------\n";
        }
        return msg;
    }
    

    public Rental findByCarCode(int carCode) {
        for (Rental r : rentals) {
            if (r.getCar().getCode() == carCode) {
                return r;
            }
        }
        return null;
    }
    
    public ArrayList<Rental> getRentals() {
        return rentals;
    }

    public int getTotalRentals() {
        return getRentals().size();
    }
}
