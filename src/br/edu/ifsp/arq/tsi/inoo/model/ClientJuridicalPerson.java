package br.edu.ifsp.arq.tsi.inoo.model;

public class ClientJuridicalPerson extends Client {
    private String cnpj;
    private String corporateReason;

    public ClientJuridicalPerson(int id, String name, String cnpj, String corporateReason) {
        super(id, name);
        this.cnpj = cnpj;
        this.corporateReason = corporateReason;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCorporateReason() {
        return corporateReason;
    }

    public void setCorporateReason(String corporateReason) {
        this.corporateReason = corporateReason;
    }

    @Override
    public String toString() {
        return "ClientJuridicalPerson [cnpj=" + cnpj + ", corporateReason=" + corporateReason + "]";
    }
}