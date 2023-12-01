package br.edu.ifsp.arq.tsi.inoo.model;

public class ClientNaturalPerson extends Client {
    private String cpf;

    public ClientNaturalPerson(String name, String cpf) {
        super(name);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ClientNaturalPerson [cpf=" + cpf + "]";
    }
}