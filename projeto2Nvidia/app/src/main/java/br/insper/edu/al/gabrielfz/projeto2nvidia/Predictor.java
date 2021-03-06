package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Predictor {
    private final int scientistsNumber;
    private final String dataType;
    private final int Projects;
    private final double dataSetSize;
    private final String usageType;
    private final int time;
    private final int applications;
    private final CloudServer p32Large;
    private final CloudServer p38Large;
    private final CloudServer p316Large;
    private final PhysicalServer Geforce2080;
    private final PhysicalServer Titan;
    private final PhysicalServer doubleT4;
    private final PhysicalServer quadrupleT4;
    private final PhysicalServer doubleRTX;
    private final PhysicalServer quadrupleV100;
    private final PhysicalServer eightupleV100;
    private double dollar;
    private boolean isDone;

    public Predictor(int time, int applications, int scientistsNumber, String dataType, int Projects, double dataSetSize, String usageType,String dollar){
        this.dollar = Double.parseDouble(dollar.replace(",","."));
        this.time = time;
        this.isDone = false;
        this.applications = applications;
        this.scientistsNumber = scientistsNumber;
        this.dataType = dataType;
        this.Projects = Projects;
        this.dataSetSize = dataSetSize;
        this.usageType = usageType;
        this.p32Large = new CloudServer("Amazon p3.2xlarge",1,false,16,8,61,
                10,1.5,3.06*this.dollar, scientistsNumber, time*30*16);

        this.p38Large = new CloudServer("Amazon p3.8xlarge", 4, true, 64,	32,
                244,	10,	7,12.24*this.dollar, 1, time*30*16);

        this.p316Large = new CloudServer("Amazon p3.16xlarge",8,true,128,64,488,
                25,14,24.48*this.dollar, 1, time*30*16);

        this.Geforce2080 = new PhysicalServer("Geforce 2080TI",11, 1870*this.dollar, scientistsNumber, 1);
        this.Titan = new PhysicalServer("Workstation Titan",24, 12000*this.dollar, scientistsNumber, 1);
        this.doubleT4 = new PhysicalServer("Servidor com duas Tesla T4",32, 20000*this.dollar, 1, 2);
        this.quadrupleT4 = new PhysicalServer("Servidor com quatro Tesla T4",64, 25000*this.dollar, 1, 4);
        this.doubleRTX = new PhysicalServer("Servidor com duas RTX 6000",48, 42000*this.dollar, 1, 2);
        this.quadrupleV100 = new PhysicalServer("Servidor DGX com 4 Tesla V100",128, 65000*this.dollar, 1, 4);
        this.eightupleV100 = new PhysicalServer("Servidor DGX-1 com 8 Tesla V100",256, 211000*this.dollar, 1, 8);

    }

//    CloudServer p324Large = new CloudServer("p3dn.24xlarge",8,true,256,96,
//            768,100,14,31.218, 1);


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
        return p316Large;
    }

    public PhysicalServer predictServer() {
        if(usageType.equals("Treinamento") || usageType.equals("Treinamento + Inferência")){
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
        return eightupleV100;
    }

    public Server bestSolution(){
        if(this.predictServer().getPrice() < this.predictCloud().getPrice()){
            return this.predictServer();
        }

        else{
            return this.predictCloud();
        }
    }

}
