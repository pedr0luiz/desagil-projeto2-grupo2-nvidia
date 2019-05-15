package br.insper.edu.al.gabrielfz.projeto2nvidia;

public class CloudServer extends Server{
    protected final int gpuNumber;
    protected final boolean nvLink;
    protected final int vCPU;
    protected final int ram;
    protected final int bandWidth;
    protected final double bandWidthEBS;

    public CloudServer(String name, int gpuNumber, boolean nvLink, int gpuMemory, int vCPU, int ram,
                       int bandWidth, double bandWidthEBS, double price){
        super(name,gpuMemory,price);
        this.gpuNumber = gpuNumber;
        this.nvLink = nvLink;
        this.vCPU = vCPU;
        this.ram = ram;
        this.bandWidth = bandWidth;
        this.bandWidthEBS = bandWidthEBS;
    }

    public int getGpuNumber() {
        return gpuNumber;
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