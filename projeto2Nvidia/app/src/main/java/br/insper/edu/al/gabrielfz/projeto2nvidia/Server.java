package br.insper.edu.al.gabrielfz.projeto2nvidia;

public abstract class Server {
    protected double price;
    protected int gpuMemory;
    protected String name;
    protected int quantity;

    public Server(String name, int gpuMemory, double price, int quantity){
        this.name = name;
        this.gpuMemory = gpuMemory;
        this.quantity = quantity;
        this.price = price*quantity;
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
}
