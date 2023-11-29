package br.edu.ifsp.arq.tsi.inoo.controller;

import java.util.ArrayList;
import br.edu.ifsp.arq.tsi.inoo.model.Client;

public class ClientController {
    private static ClientController instance;

    private ArrayList<Client> clients;
    private int nextId;

    private ClientController() {
        clients = new ArrayList<>();
    }

    public static synchronized ClientController getInstance() {
        if (instance == null) {
            return new ClientController();
        }
        return instance;
    }

    public boolean save (Client client) {
        if (client != null) {
            client.setId(nextId++);
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
}