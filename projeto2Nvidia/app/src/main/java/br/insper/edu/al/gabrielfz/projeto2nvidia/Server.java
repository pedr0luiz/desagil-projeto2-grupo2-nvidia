package br.insper.edu.al.gabrielfz.projeto2nvidia;

public abstract class Server {
    protected double price;
    protected int gpuMemory;
    protected String name;

    public Server(String name, int gpuMemory, double price){
        this.name = name;
        this.price = price;
        this.gpuMemory = gpuMemory;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getGpuMemory() {
        return gpuMemory;
    }
}
