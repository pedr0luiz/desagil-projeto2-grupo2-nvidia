package br.insper.edu.al.gabrielfz.projeto2nvidia;

public class Predictor {
    private final int scientistsNumber;
    private final String dataType;
    private final int simultaneousProjects;
    private final double dataSetSize;

    public Predictor(int scientistsNumber, String dataType, int simultaneousProjects, double dataSetSize){
        this.scientistsNumber = scientistsNumber;
        this.dataType = dataType;
        this.simultaneousProjects = simultaneousProjects;
        this.dataSetSize = dataSetSize;
    }

    CloudServer p32Large = new CloudServer("p3.2xlarge",1,false,16,8,61,
            10,1.5,3.06);
    CloudServer p38Large = new CloudServer("p3.8xlarge", 4, true, 64,	32,
            244,	10,	7,12.24);

    CloudServer p316Large = new CloudServer("p3.16xlarge",8,true,128,64,488,
            25,14,24.48);
    CloudServer p324Large = new CloudServer("p3dn.24xlarge",8,true,256,96,
            768,100,14,31.218);

    PhysicalServer teste = new PhysicalServer("Teste",30, 3.45);

    public double precictTime(){
        return 1;
    }

    public CloudServer predictCloud(){
        if (dataType == "video"){
            return p32Large;
        }
        return p316Large;
    }

    public double predictCloudPrice(){
        return this.predictCloud().price*this.precictTime();
    }

    public PhysicalServer predictServer(){
        return teste;
    }

    public Server bestSolution(){
        if(this.predictServer().getPrice() > this.predictCloudPrice()){
            return this.predictServer();
        }

        else{
            return this.predictCloud();
        }
    }
}
