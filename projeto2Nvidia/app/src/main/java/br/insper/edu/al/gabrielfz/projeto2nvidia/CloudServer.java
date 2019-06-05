package br.insper.edu.al.gabrielfz.projeto2nvidia;

public class CloudServer extends Server{
    protected final boolean nvLink;
    protected final int vCPU;
    protected final int ram;
    protected final int bandWidth;
    protected final double bandWidthEBS;

    public CloudServer(String name, int gpuNumber, boolean nvLink, int gpuMemory, int vCPU, int ram,
                       int bandWidth, double bandWidthEBS, double price, int quantity, int time){
        super(name,gpuMemory,price*time, quantity, gpuNumber);
        this.gpuNumber = gpuNumber;
        this.nvLink = nvLink;
        this.vCPU = vCPU;
        this.ram = ram;
        this.bandWidth = bandWidth;
        this.bandWidthEBS = bandWidthEBS;
    }

    public boolean isNvLink() {
        return nvLink;
    }

    public int getvCPU() {
        return vCPU;
    }

    public int getRam() {
        return ram;
    }

    public int getBandWidth() {
        return bandWidth;
    }

    public double getBandWidthEBS() {
        return bandWidthEBS;
    }
}