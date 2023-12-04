package br.edu.ifsp.arq.tsi.inoo.view;

import javax.swing.JOptionPane;

import br.edu.ifsp.arq.tsi.inoo.controller.*;
import br.edu.ifsp.arq.tsi.inoo.model.*;


public class Menu {
    public static void main(String[] args) {
        RentalController rentalController = RentalController.getInstance();
        ClientController clientController = ClientController.getInstance();
        CarController carController = CarController.getInstance();

        int option;

        do {
            String[] options = {"Inserir Cliente", "Inserir Carro", "Realizar Locação", "Realizar Devolução", "Gerar Relatório", "Sair"};
            option = JOptionPane.showOptionDialog(null, "Sistema de Locação de Carros", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0:
                    String name = JOptionPane.showInputDialog("Digite o nome do cliente:");
                    String document = JOptionPane.showInputDialog("Digite o CPF ou CNPJ do cliente:");

                    Client client = new ClientNaturalPerson(name, document);

                    if (clientController.save(client)) {
                        JOptionPane.showMessageDialog(null, "Cliente inserido:\nNome: " + name + "\nCPF/CNPJ: " + document);
                    }
                    break;
                case 1:
                    String model = JOptionPane.showInputDialog("Digite o modelo do carro");
                    String brand = JOptionPane.showInputDialog("Digite a marca do carro");
                    int year = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro"));
                    String plate = JOptionPane.showInputDialog("Digite a placa do carro");
                    int numberDoors = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de portas do carro"));
                    boolean hasAirConditioning = JOptionPane.showInputDialog("O carro possui ar condicionado?").equals("Sim") ? true : false;
                    double dailyRate = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da diária do carro"));

                    Car car = new Car(model, brand, year, plate, numberDoors, hasAirConditioning, dailyRate);

                    if(carController.save(car)){
                        JOptionPane.showMessageDialog(null, "Carro cadastrado com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar carro!");
                    }
                    break;
                case 2:
                    int clientCode = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente"));
                    int carCode = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do carro"));
                    int numberDiaries = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de diárias"));

                    Rental rental = new Rental(numberDiaries, clientController.findById(clientCode), carController.findByCode(carCode));

                    if(rentalController.rental(rental)){
                        JOptionPane.showMessageDialog(null, "Carro alugado com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao alugar carro!");
                    }
                    break;
                case 3:
                    carCode = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do carro"));

                    if(rentalController.devolution(rentalController.findByCarCode(carCode))){
                        JOptionPane.showMessageDialog(null, "Carro devolvido com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao devolver carro!");
                    }
                    break;
                case 4:
                    String report = rentalController.generateReport();
                    JOptionPane.showMessageDialog(null, report);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saindo do sistema. Até mais!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                    break;
            }

        } while (option != 5);
    }
}

