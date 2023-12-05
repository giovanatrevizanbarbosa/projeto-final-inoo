package br.edu.ifsp.arq.tsi.inoo.controller;

import java.util.ArrayList;
import br.edu.ifsp.arq.tsi.inoo.model.Car;

public class CarController {
    private static CarController instance;

    private ArrayList<Car> cars;
    private int nextCode;

    private CarController() {
        cars = new ArrayList<>();
        nextCode = 1;
    }

    public static synchronized CarController getInstance() {
        if (instance == null) {
            return new CarController();
        }
        return instance;
    }

    public boolean save(Car car) {
        if (car != null) {
            car.setCode(nextCode++);
            cars.add(car);
            return true;
        }
        return false;
    }

    public boolean delete(Car car) {
        return cars.remove(car);
    }

    public Car findByCode(int code) {
        for (Car c : cars) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public int getTotalCars() {
        return getCars().size();
    }

    public String generateReport() {
        String msg = "RELATÓRIO DE CARROS: " + getTotalCars() + "\n";
        msg += "---------------------------------------------------\n";
        for (Car c : cars) {
            msg += "Carro: " + c.getCode() + "\n";
            msg += "Modelo: " + c.getModel() + "\n";
            msg += "Marca: " + c.getBrand() + "\n";
            msg += "Ano: " + c.getYear() + "\n";
            msg += "Placa: " + c.getLicensePlate() + "\n";
            msg += "Número de Portas: " + c.getQuantityDoors() + "\n";
            msg += "Ar Condicionado: " + (c.hasAirConditioning() ? "Sim" : "Não") + "\n";
            msg += "Valor da Diária: " + c.getDailyRate() + "\n";
            msg += "Status: " + (c.getStatus() ? "Disponível" : "Alugado") + "\n";
            msg += "---------------------------------------------------\n";
        }
        return msg;
    }
}