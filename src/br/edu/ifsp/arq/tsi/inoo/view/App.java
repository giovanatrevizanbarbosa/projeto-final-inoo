package br.edu.ifsp.arq.tsi.inoo.view;
import br.edu.ifsp.arq.tsi.inoo.controller.RentalController;
import br.edu.ifsp.arq.tsi.inoo.model.*;
import java.time.LocalDate;
/**
 * Hello world!
 *
 */

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Coming soon!");
        // exemplo de locacao
        RentalController rentalController = RentalController.getInstance();

        Client client = new ClientNaturalPerson(0, "Gigi", "123456");
        
        Car car = new Car(0, "Gol", "Volkswagen", 2020, "ABC1234", 4, true, 100.0);

        Rental rental = new Rental(5, client, car);
        
        if(rentalController.rental(rental)){
            System.out.println("Aluguel realizado com sucesso!");
        }
        // exemplo de devolucao
        if(rentalController.devolution(rental)){
            System.out.println("Devolução realizada com sucesso!");
        }
        // exemplo de relatorio
        System.out.println(rentalController.generateReport());

        System.out.println(rentalController.getRentals());
    }
}