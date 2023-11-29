package br.edu.ifsp.arq.tsi.inoo.controller;

import br.edu.ifsp.arq.tsi.inoo.model.Rental;
import java.util.ArrayList;

public class RentalController {
    private static RentalController instance;

    private ArrayList<Rental> rentals;
    private int nextCode;

    private RentalController() {
        rentals = new ArrayList<>();
    }

    public static synchronized RentalController getInstance() {
        if (instance == null) {
            return new RentalController();
        }
        return instance;
    }

    public boolean rental(Rental rental) {
        if (rental != null) {
            rental.setNumber(nextCode);
            rentals.add(rental);
            return true;
        }
        return false;
    }

    public boolean devolution(Rental rental) {
        if (rental != null) {
            rental.setNumber(nextCode);
            rentals.add(rental);
        }
        return rentals.remove(rental);
    }

    public String generateReport() {
        String msg = "RELATÓRIO DE ALUGUÉIS\n";
        msg += "---------------------------------------------------\n";
        for (Rental r : rentals) {
            msg += "Aluguel: " + r.getNumber() + "\n";
            msg += "Data da realização: " + r.getDayRental() + "\n";
            msg += "Número de diárias: " + r.getNumberDiaries() + "\n";
            msg += "Data máxima para devolução: " + r.getMaxDate() + "\n";
            msg += "Data de devolução: " + r.getReturnDate() + "\n";
            msg += "Data de devolução: " + r.getReturnDate() + "\n";
            msg += "Cliente: " + r.getClient() + "\n";
            msg += "Carro escolhido: " + r.getCar() + "\n";
            msg += "---------------------------------------------------\n";
        }
        return msg;
    }

    public ArrayList<Rental> getRentals() {
        return rentals;
    }
}