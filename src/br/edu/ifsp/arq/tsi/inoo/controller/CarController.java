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
}