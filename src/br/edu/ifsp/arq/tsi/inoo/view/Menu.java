package br.edu.ifsp.arq.tsi.inoo.view;

import javax.swing.JOptionPane;

public class Menu {
    public static void main(String[] args) {
        int option;

        do {
            String[] options = {"Inserir Cliente", "Inserir Carro", "Realizar Locação", "Realizar Devolução", "Gerar Relatório", "Sair"};
            option = JOptionPane.showOptionDialog(null, "Sistema de Locação de Carros", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0:
                    String name = JOptionPane.showInputDialog("Digite o nome do cliente:");
                    String document = JOptionPane.showInputDialog("Digite o CPF ou CNPJ do cliente:");

                    JOptionPane.showMessageDialog(null, "Cliente inserido:\nNome: " + name + "\nCPF/CNPJ: " + document);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Opção escolhida: Inserir Carro");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Opção escolhida: Realizar Locação");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Opção escolhida: Realizar Devolução");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Opção escolhida: Gerar Relatório");
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

