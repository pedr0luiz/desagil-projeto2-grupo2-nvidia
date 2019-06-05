package br.insper.edu.al.gabrielfz.projeto2nvidia;

public abstract class Server {
    protected double price;
    protected int gpuMemory;
    protected String name;
    protected int quantity;
    protected int gpuNumber;

    public Server(){

    }

    public Server(String name, int gpuMemory, double price, int quantity, int gpuNumber){
        this.name = name;
        this.gpuMemory = gpuMemory;
        this.quantity = quantity;
        this.price = price*quantity;
        this.gpuNumber = gpuNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() { return quantity; }

    public int getGpuMemory() {
        return gpuMemory;
    }

    public int getGpuNumber() {
        return gpuNumber;
    }
}
