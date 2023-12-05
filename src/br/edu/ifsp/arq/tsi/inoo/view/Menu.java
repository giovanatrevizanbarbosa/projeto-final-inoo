package br.edu.ifsp.arq.tsi.inoo.view;

import javax.swing.JOptionPane;

import br.edu.ifsp.arq.tsi.inoo.controller.*;
import br.edu.ifsp.arq.tsi.inoo.model.*;

public class Menu {
    public static void main(String[] args) {
        RentalController rentalController = RentalController.getInstance();
        ClientController clientController = ClientController.getInstance();
        CarController carController = CarController.getInstance();

        int option, confirm;

        do {
            String[] options = {"Inserir Cliente", "Inserir Carro", "Realizar Locação", "Realizar Devolução", "Gerar Relatório", "Sair"};
            option = JOptionPane.showOptionDialog(null, "Sistema de Locação de Carros", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0:
                    String name = JOptionPane.showInputDialog("Digite o nome do cliente:");
                    String document = JOptionPane.showInputDialog("Digite o CPF ou CNPJ do cliente:");
                    confirm = JOptionPane.showConfirmDialog(null, "\nDeseja confirmar o cadastro?" 
                                                                + "\nCliente inserido:\nNome: " + name 
                                                                + "\nCPF/CNPJ: " + document, "Cadastro de Cliente", JOptionPane.YES_NO_OPTION);

                    Client client = new ClientNaturalPerson(name, document);
                    if(confirm == 0 && clientController.save(client)){
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                    }
                    break;
                case 1:
                    String model = JOptionPane.showInputDialog("Digite o modelo do carro");
                    String brand = JOptionPane.showInputDialog("Digite a marca do carro");
                    int year = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano do carro"));
                    String plate = JOptionPane.showInputDialog("Digite a placa do carro");
                    int numberDoors = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de portas do carro"));
                    boolean hasAirConditioning = JOptionPane.showInputDialog("O carro possui ar condicionado?").equalsIgnoreCase("Sim") ? true : false;
                    double dailyRate = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da diária do carro"));

                    Car car = new Car(model, brand, year, plate, numberDoors, hasAirConditioning, dailyRate);

                    String hasAir = hasAirConditioning ? "Possui" : "Não possui";
                    confirm = JOptionPane.showConfirmDialog(null, "\nDeseja confirmar o cadastro?"
                                                                + "\nModelo: " + model 
                                                                + "\nMarca: " + brand 
                                                                + "\nAno: " + year 
                                                                + "\nPlaca: " + plate 
                                                                + "\nNúmero de portas: " + numberDoors 
                                                                + "\nAr condicionado: " + hasAir
                                                                + "\nValor da diária: " + dailyRate, "Confirmação do cadastro", JOptionPane.YES_NO_OPTION);

                    if (carController.save(car) && confirm == 0) {
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
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

