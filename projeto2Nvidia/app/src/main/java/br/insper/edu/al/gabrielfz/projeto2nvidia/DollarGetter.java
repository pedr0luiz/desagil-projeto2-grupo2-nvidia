package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DollarGetter extends AsyncTask<String, Integer, Long> {
    public boolean isDone;
    public String dollar;
    public DollarGetter(){
        super();
        isDone = false;
    }

    @Override
    public Long doInBackground(String... params){
        try {
            System.out.println("RODEI");
            URL url = new URL("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='06-05-2019'&$top=1&$format=text/csv");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            this.dollar = content.toString().split("\"")[1];
            in.close();
        } catch(IOException e){
            System.out.println("ERROR");
        }
        long someLong = (long) 0.1;
        return someLong;
    }

    @Override
    public void onPostExecute(Long result) {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }
}
