package br.insper.edu.al.gabrielfz.projeto2nvidia;

public class Predictor {
    private final int scientistsNumber;
    private final String dataType;
    private final int simultaneousProjects;
    private final double dataSetSize;
    private final String usageType;

    public Predictor(int scientistsNumber, String dataType, int simultaneousProjects, double dataSetSize,String usageType){
        this.scientistsNumber = scientistsNumber;
        this.dataType = dataType;
        this.simultaneousProjects = simultaneousProjects;
        this.dataSetSize = dataSetSize;
        this.usageType = usageType;
    }

    CloudServer p32Large = new CloudServer("p3.2xlarge",1,false,16,8,61,
            10,1.5,3.06);
    CloudServer p38Large = new CloudServer("p3.8xlarge", 4, true, 64,	32,
            244,	10,	7,12.24);

    CloudServer p316Large = new CloudServer("p3.16xlarge",8,true,128,64,488,
            25,14,24.48);
    CloudServer p324Large = new CloudServer("p3dn.24xlarge",8,true,256,96,
            768,100,14,31.218);

    PhysicalServer Geforce2080 = new PhysicalServer("Geforce 2080TI",11, 1870);
    PhysicalServer Titan = new PhysicalServer("Workstation Titan",24, 12000);
    PhysicalServer doubleT4 = new PhysicalServer("Servidor com duas Tesla T4",32, 20000);
    PhysicalServer quadrupleT4 = new PhysicalServer("Servidor com quatro Tesla T4",64, 25000);
    PhysicalServer doubleRTX = new PhysicalServer("Servidor com duas RTX 6000",48, 42000);
    PhysicalServer quadrupleV100 = new PhysicalServer("Servidor DGX com 4 Tesla V100",128, 65000);
    PhysicalServer eightupleV100 = new PhysicalServer("Servidor DGX-1 com 8 Tesla V100",256, 211000);




    public double precictTime(){
        int time = 0;
        time += 20*scientistsNumber;
        time += 20*simultaneousProjects;
        switch (usageType){
            case "Treinamento":
                time *= 1.2;
            case "Inferencia":
                time *= 1.5;
            case "Treinamento + Inferencia":
                time *= 1.85;
        }
        return time;
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

    public PhysicalServer predictServer() {
        int pequeno = 250;
        int medio = 500;
        int grande = 1000;
        int gigante = 4000;

        if (scientistsNumber == 1 && simultaneousProjects == 1 && dataSetSize <= pequeno) {
            return Geforce2080;
        } else if (scientistsNumber <= 5 && simultaneousProjects <= 2 && dataSetSize <= pequeno) {
            return Titan;
        } else if (scientistsNumber <= 10 && simultaneousProjects <= 6 && dataSetSize <= medio) {
            if (usageType == "Treinamento") {
                return doubleT4;
            } else
                return quadrupleT4;
        } else if (scientistsNumber > 10 && scientistsNumber < 18 || simultaneousProjects > 6 && simultaneousProjects < 15 && dataSetSize <= grande) {
            return quadrupleV100;
        } else if (scientistsNumber >= 18 || simultaneousProjects >= 15 && dataSetSize <= gigante) {
            return eightupleV100;
        }
        else{
            return doubleRTX;
        }
    }

    public Server bestSolution(){
        if(this.predictServer().getPrice() < this.predictCloudPrice()){
            return this.predictServer();
        }

        else{
            return this.predictCloud();
        }
    }
}
