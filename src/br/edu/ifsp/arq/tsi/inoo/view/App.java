package br.edu.ifsp.arq.tsi.inoo.view;
import br.edu.ifsp.arq.tsi.inoo.controller.*;
import br.edu.ifsp.arq.tsi.inoo.model.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Coming soon!");
        // exemplo de locacao
        RentalController rentalController = RentalController.getInstance();
        ClientController clientController = ClientController.getInstance();
        CarController carController = CarController.getInstance();

        Client client = new ClientNaturalPerson("Gigi", "123456");
        clientController.save(client);
        
        Car car = new Car("Gol", "Volkswagen", 2020, "ABC1234", 4, true, 100.0);
        carController.save(car);

        Rental rental = new Rental(5, client, car);
        rentalController.rental(rental);

        car = new Car("GTR", "Nissan", 2023, "JGF2345", 2, true, 1000.0);
        carController.save(car);

        client = new ClientNaturalPerson("Natan", "239487234");
        clientController.save(client);

        rental = new Rental(7, client, car);

        
        if(rentalController.rental(rental)){
            System.out.println("Aluguel realizado com sucesso!");
        }
        
        //exemplo de relatorio
        System.out.println(rentalController.generateReport());
        
        //System.out.println(rentalController.getRentals());
        
        System.out.println(rentalController.getClientRentals(client));

        //exemplo de devolucao
        if(rentalController.devolution(rental)){
            System.out.println("Devolução realizada com sucesso!");
            System.out.println("Status do carro: " + car.getStatus());
        }

        System.out.println(rentalController.generateReport());
    }
}