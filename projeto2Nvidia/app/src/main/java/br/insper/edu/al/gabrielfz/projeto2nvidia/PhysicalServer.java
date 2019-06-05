package br.insper.edu.al.gabrielfz.projeto2nvidia;

import java.io.Serializable;

public class PhysicalServer extends Server implements Serializable {

    public PhysicalServer(String name, int gpuMemory, double price, int quantity, int gpuNumber){
        super(name, gpuMemory, price, quantity, gpuNumber);
    }

}
