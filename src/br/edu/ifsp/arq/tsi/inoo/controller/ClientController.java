package br.edu.ifsp.arq.tsi.inoo.controller;

import java.util.ArrayList;
import br.edu.ifsp.arq.tsi.inoo.model.Client;
import br.edu.ifsp.arq.tsi.inoo.model.ClientJuridicalPerson;
import br.edu.ifsp.arq.tsi.inoo.model.ClientNaturalPerson;

public class ClientController {
    private static ClientController instance;

    private ArrayList<Client> clients;
    private int nextId;

    private ClientController() {
        clients = new ArrayList<>();
        nextId = 1;
    }

    public static synchronized ClientController getInstance() {
        if (instance == null) {
            return new ClientController();
        }
        return instance;
    }

    public boolean validate(Client client) {
        if (client instanceof ClientNaturalPerson) {
            for (Client c : clients) {
                if (c instanceof ClientNaturalPerson) {
                    if (((ClientNaturalPerson) c).getCpf().equals(((ClientNaturalPerson) client).getCpf())) {
                        return false;
                    }
                }
            }
        } else if (client instanceof ClientJuridicalPerson) {
            for (Client c : clients) {
                if (c instanceof ClientJuridicalPerson) {
                    if (((ClientJuridicalPerson) c).getCnpj().equals(((ClientJuridicalPerson) client).getCnpj())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean save(Client client) {
        if (client != null && validate(client)){
            client.setId(nextId++);
            clients.add(client);
            return true;
        }
        return false;
    }

    public boolean delete(Client client){
        return clients.remove(client);
    }

    public Client findById(int id) {
        for (Client c : clients) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public int getTotalClients() {
        return getClients().size();
    }

    public String generateReport() {
        String msg = "RELATÓRIO DE CLIENTES: " + getTotalClients() + "\n";
        msg += "---------------------------------------------------\n";
        for (Client c : clients) {
            msg += "Cliente: " + c.getId() + "\n";
            msg += "Nome: " + c.getName() + "\n";
    
            if (c instanceof ClientNaturalPerson) {
                msg += "Documento (CPF): " + ((ClientNaturalPerson) c).getCpf() + "\n";
            } else if (c instanceof ClientJuridicalPerson) {
                ClientJuridicalPerson juridicalPerson = (ClientJuridicalPerson) c;
                msg += "Documento (CNPJ): " + juridicalPerson.getCnpj() + "\n";
                msg += "Razão Social: " + juridicalPerson.getCorporateReason() + "\n";
            }

            msg += "---------------------------------------------------\n";
        }
        return msg;
    }
}