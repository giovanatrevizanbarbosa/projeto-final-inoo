package br.edu.ifsp.arq.tsi.inoo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.ifsp.arq.tsi.inoo.controller.*;
import br.edu.ifsp.arq.tsi.inoo.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeWindow extends JFrame implements ActionListener{
    private JPanel panel;
    private JButton addClientButton, addCarButton, rentalButton, devolutionButton, reportButton;
    private JButton addClientNaturalPerson, addClientJuridicalPerson;
    private static final String TITLE = "Locadora de Carros";

    private ClientController clientController = ClientController.getInstance();
    private CarController carController = CarController.getInstance();
    private RentalController rentalController = RentalController.getInstance();

    public HomeWindow(){
        // instancia os componentes
        createComponents();
        // adiciona componentes
        addComponents();
        // configura a janela
        configureWindow();
    }

    private void createComponents(){
        panel = new JPanel();
        addClientButton = new JButton("Cadastrar Cliente");
        addClientNaturalPerson = new JButton("Pessoa Física");
        addClientJuridicalPerson = new JButton("Pessoa Jurídica");
        addCarButton = new JButton("Cadastrar Carro");
        rentalButton = new JButton("Alugar Carro");
        devolutionButton = new JButton("Devolver Carro");
        reportButton = new JButton("Gerar Relatório");
    }

    private void addComponents(){
        panel.add(addClientButton);
        panel.add(addCarButton);
        panel.add(rentalButton);
        panel.add(devolutionButton);
        panel.add(reportButton);
        panel.add(addClientNaturalPerson);
        panel.add(addClientJuridicalPerson);
        addClientButton.addActionListener(this);
        addCarButton.addActionListener(this);
        rentalButton.addActionListener(this);
        devolutionButton.addActionListener(this);
        reportButton.addActionListener(this);
        addClientNaturalPerson.addActionListener(this);
        addClientJuridicalPerson.addActionListener(this);
        addClientNaturalPerson.setVisible(false);
        addClientJuridicalPerson.setVisible(false);
        add(panel);
    }

    private void configureWindow(){
        setTitle(TITLE);
        setSize(150, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }   

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == addClientButton){
            String clientType = JOptionPane.showInputDialog("Tipo de cliente", "Pessoa Física ou Pessoa Jurídica");
            Client client = getClientType(clientType);
            if(clientController.save(client)){
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente!");
            }
        }else if(event.getSource() == addCarButton){
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
        }else if(event.getSource() == rentalButton){
            int clientCode = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente"));
            int carCode = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do carro"));
            int numberDiaries = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de diárias"));
            Rental rental = new Rental(numberDiaries, clientController.findById(clientCode), carController.findByCode(carCode));
            if(rentalController.rental(rental)){
                JOptionPane.showMessageDialog(null, "Carro alugado com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao alugar carro!");
            }

        }else if(event.getSource() == devolutionButton){
            int carCode = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do carro"));
            if(rentalController.devolution(rentalController.findByCarCode(carCode))){
                JOptionPane.showMessageDialog(null, "Carro devolvido com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao devolver carro!");
            }
        }else if(event.getSource() == reportButton){
            JOptionPane.showMessageDialog(null, rentalController.generateReport());
        }
    }

    public Client getClientType(String clientType){
        if(clientType.equals("Pessoa Física")){
            String name = JOptionPane.showInputDialog("Digite o nome do cliente");
            String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente");
            ClientNaturalPerson client = new ClientNaturalPerson(name, cpf);
            return client;
        }else if(clientType.equals("Pessoa Jurídica")){
            String name = JOptionPane.showInputDialog("Digite o nome do cliente");
            String cnpj = JOptionPane.showInputDialog("Digite o CNPJ do cliente");
            String socialReason = JOptionPane.showInputDialog("Digite a razão social do cliente");
            ClientJuridicalPerson client = new ClientJuridicalPerson(name, cnpj, socialReason);
            return client;
        }
        return null;
    }

}
