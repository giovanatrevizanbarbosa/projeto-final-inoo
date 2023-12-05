package br.edu.ifsp.arq.tsi.inoo.view;

import br.edu.ifsp.arq.tsi.inoo.controller.CarController;
import br.edu.ifsp.arq.tsi.inoo.controller.ClientController;
import br.edu.ifsp.arq.tsi.inoo.controller.RentalController;
import br.edu.ifsp.arq.tsi.inoo.model.Car;
import br.edu.ifsp.arq.tsi.inoo.model.Client;
import br.edu.ifsp.arq.tsi.inoo.model.ClientJuridicalPerson;
import br.edu.ifsp.arq.tsi.inoo.model.ClientNaturalPerson;
import br.edu.ifsp.arq.tsi.inoo.model.Rental;

import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RentalController rentalController = RentalController.getInstance();
    private static final ClientController clientController = ClientController.getInstance();
    private static final CarController carController = CarController.getInstance();

    public static void main(String[] args) {
        int option;

        do {
            printMenu();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    insertClient();
                    break;
                case 2:
                    insertCar();
                    break;
                case 3:
                    rentCar();
                    break;
                case 4:
                    devolutionCar();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    System.out.println("\nSaindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    break;
            }
        } while (option != 6);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Sistema de Locação de Carros");
        System.out.println("1. Inserir Cliente");
        System.out.println("2. Inserir Carro");
        System.out.println("3. Realizar Locação");
        System.out.println("4. Realizar Devolução");
        System.out.println("5. Gerar Relatório");
        System.out.println("6. Sair");
    }

    private static void insertClient() {
        System.out.print("\nDigite o nome do cliente: ");
        String name = scanner.next();

        System.out.println("Escolha o tipo de documento:");
        System.out.println("1. CPF");
        System.out.println("2. CNPJ");
        System.out.print("Digite o número correspondente: ");
        int documentTypeChoice = scanner.nextInt();

        String documentType = (documentTypeChoice == 1) ? "CPF" : "CNPJ";

        System.out.print("Digite o número do " + documentType + ": ");
        String document = scanner.next();

        Client client;
        String corporateReason = "";
        if ("CPF".equals(documentType)) {
            client = new ClientNaturalPerson(name, document);
        } else {
            System.out.print("Digite a Razão Social da empresa: ");
            corporateReason = scanner.next();

            client = new ClientJuridicalPerson(name, document, corporateReason);
        }

        System.out.println("\nDeseja confirmar o cadastro?");
        System.out.println("Cliente inserido:\nNome: " + name + "\nCPF/CNPJ: " + document);

        if ("CNPJ".equals(documentType)) {
            System.out.println("Razão Social: " + corporateReason);
        }

        System.out.print("1 para confirmar, 0 para cancelar: ");
        int confirm = scanner.nextInt();

        if (confirm == 1 && clientController.save(client)) {
            System.out.println("Cadastro realizado com sucesso!\n");
        } else {
            System.out.println("Cadastro cancelado\n");
        }
    }

    private static void insertCar() {
        System.out.print("\nDigite o modelo do carro: ");
        String model = scanner.next();
        System.out.print("Digite a marca do carro: ");
        String brand = scanner.next();
        System.out.print("Digite o ano do carro: ");
        int year = scanner.nextInt();
        System.out.print("Digite a placa do carro: ");
        String plate = scanner.next();
        System.out.print("Digite o número de portas do carro: ");
        int numberDoors = scanner.nextInt();
        System.out.print("O carro possui ar condicionado? (Sim/Não): ");
        boolean hasAirConditioning = scanner.next().equalsIgnoreCase("Sim");
        System.out.print("Digite o valor da diária do carro: ");
        double dailyRate = scanner.nextDouble();

        Car car = new Car(model, brand, year, plate, numberDoors, hasAirConditioning, dailyRate);

        String hasAir = hasAirConditioning ? "Possui" : "Não possui";
        System.out.println("\nDeseja confirmar o cadastro?");
        System.out.println("Modelo: " + model + "\nMarca: " + brand + "\nAno: " + year + "\nPlaca: " + plate
                        + "\nNúmero de portas: " + numberDoors + "\nAr condicionado: " + hasAir
                        + "\nValor da diária: " + dailyRate);
        System.out.print("1 para confirmar, 0 para cancelar: ");
        int confirm = scanner.nextInt();

        if (carController.save(car) && confirm == 1) {
            System.out.println("Cadastro realizado com sucesso!\n");
        } else {
            System.out.println("Cadastro cancelado.\n");
        }
    }

    private static void rentCar() {
        if (clientController.getTotalClients() > 0 && carController.getTotalCars() > 0) {
            System.out.println(clientController.generateReport());
            System.out.print("Digite o código do cliente: ");
            int clientCode = scanner.nextInt();
            System.out.println(carController.generateReport());
            System.out.print("Digite o código do carro: ");
            int carCode = scanner.nextInt();
            System.out.print("Digite o número de diárias: ");
            int numberDiaries = scanner.nextInt();

            Rental rental = new Rental(numberDiaries, clientController.findById(clientCode),
                    carController.findByCode(carCode));

            if (rentalController.rental(rental)) {
                System.out.println("Carro alugado com sucesso!\n");
            } else {
                System.out.println("Erro ao alugar carro!\n");
            }
        } else {
            System.out.println("Locação inválida, pois não há clientes ou carros disponíveis.\n");
        }
    }

    private static void devolutionCar() {
        System.out.println(carController.generateReport());
        System.out.print("Digite o código do carro: ");
        int carCode = scanner.nextInt();

        if (rentalController.devolution(rentalController.findByCarCode(carCode))) {
            System.out.println("Carro devolvido com sucesso!\n");
        } else {
            System.out.println("Erro ao devolver carro!\n");
        }
    }

    private static void generateReport() {
        System.out.println();
        System.out.println(
                clientController.generateReport() + carController.generateReport() + rentalController.generateReport());
    }
}
