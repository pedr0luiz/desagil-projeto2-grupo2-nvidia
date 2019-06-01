package br.insper.edu.al.gabrielfz.projeto2nvidia;

public class Predictor {
    private final int scientistsNumber;
    private final String dataType;
    private final int Projects;
    private final double dataSetSize;
    private final String usageType;
    private final int time;
    private final int applications;

    public Predictor(int time, int applications, int scientistsNumber, String dataType, int Projects, double dataSetSize,String usageType){
        this.time = time*30*16;
        this.applications = applications;
        this.scientistsNumber = scientistsNumber;
        this.dataType = dataType;
        this.Projects = Projects;
        this.dataSetSize = dataSetSize;
        this.usageType = usageType;
    }

    CloudServer p32Large = new CloudServer("p3.2xlarge",1,false,16,8,61,
            10,1.5,3.06, 2);
    CloudServer p38Large = new CloudServer("p3.8xlarge", 4, true, 64,	32,
            244,	10,	7,12.24, 1);

    CloudServer p316Large = new CloudServer("p3.16xlarge",8,true,128,64,488,
            25,14,24.48, 1);

//    CloudServer p324Large = new CloudServer("p3dn.24xlarge",8,true,256,96,
//            768,100,14,31.218, 1);

    PhysicalServer Geforce2080 = new PhysicalServer("Geforce 2080TI",11, 1870, 2);
    PhysicalServer Titan = new PhysicalServer("Workstation Titan",24, 12000, 2);
    PhysicalServer doubleT4 = new PhysicalServer("Servidor com duas Tesla T4",32, 20000, 1);
    PhysicalServer quadrupleT4 = new PhysicalServer("Servidor com quatro Tesla T4",64, 25000, 1);
    PhysicalServer doubleRTX = new PhysicalServer("Servidor com duas RTX 6000",48, 42000, 1);
    PhysicalServer quadrupleV100 = new PhysicalServer("Servidor DGX com 4 Tesla V100",128, 65000, 1);
    PhysicalServer eightupleV100 = new PhysicalServer("Servidor DGX-1 com 8 Tesla V100",256, 211000, 1);


    public CloudServer predictCloud(){
        if(dataSetSize <= 875){
            return p32Large;
        }
        if(dataSetSize <= 3500 && scientistsNumber <= 4){
            return p38Large;
        }
        if(dataSetSize <= 7000 && scientistsNumber <= 8){
            return p316Large;
        }
        return null;
    }

    public double predictCloudPrice(){
        return this.predictCloud().getPrice()*this.time;
    }

    public PhysicalServer predictServer() {
        if(usageType.equals("Treinamento") || usageType.equals("Treinamento + Predição")){
            if(dataSetSize <= 300){
                return Geforce2080;
            }
            if(dataSetSize <= 600){
                return Titan;
            }
            if(dataSetSize <= 1100 && scientistsNumber <= 2){
                return doubleRTX;
            }
            if(dataSetSize <= 3500 && scientistsNumber <= 4){
                return quadrupleV100;
            }
            if(dataSetSize <= 7000 && scientistsNumber <= 8){
                return eightupleV100;
            }
        }
        else{
            if(applications <= 8){
                return doubleT4;
            }
            else if (applications <=16){
                return quadrupleT4;
            }
        }
        return null;
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
