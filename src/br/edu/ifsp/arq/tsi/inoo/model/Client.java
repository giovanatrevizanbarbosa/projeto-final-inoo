package br.edu.ifsp.arq.tsi.inoo.model;

public abstract class Client {
    protected int id;
    protected String name;

    public Client(String name) {
        this.name = name;
    }

    public Client(int id, String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client [name=" + name + "]";
    }
}