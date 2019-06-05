package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ImageView imgNavBar = findViewById(R.id.imageNavBar);
        imgNavBar.setImageResource(R.drawable.horizontallogo);

        Integer projectTime = getIntent().getIntExtra("projectTime",0);
        Integer applications = getIntent().getIntExtra("applications",0);
        Integer teamSize = getIntent().getIntExtra("teamSize",0);
        String projectType = getIntent().getStringExtra("projectType");
        Integer projects = getIntent().getIntExtra("projects",1);
        Integer databaseSize = getIntent().getIntExtra("databaseSize",0);
        String usageType = getIntent().getStringExtra("usageType");
        String dollar = getIntent().getStringExtra("dollarValue");

        Predictor predictor = new Predictor(projectTime,applications,teamSize,projectType,projects,databaseSize,usageType,dollar);

        TextView bestOptionView = findViewById(R.id.bestOption);
        bestOptionView.setText("Nome: "+predictor.bestSolution().getName());
        TextView bestOptionPriceView = findViewById(R.id.bestOptionPrice);
        bestOptionPriceView.setText("Preço: R$ " + String.valueOf(predictor.bestSolution().getPrice()));
        TextView bestOptionQuantity = findViewById(R.id.bestOptionQuantity);
        bestOptionQuantity.setText("Quantidade: "+  predictor.bestSolution().getQuantity());
        LinearLayout linearLayBestOption = findViewById(R.id.linearLay1);
        if(predictor.bestSolution().equals(predictor.predictCloud())){
            TextView txtView = new TextView(getBaseContext());
            txtView.setText("Interações para equivaler preço do servidor: "+Math.round(predictor.predictServer().getPrice()/predictor.predictCloud().getPrice()));
            txtView.setTextColor(Color.parseColor("#ffffff"));
            txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            paramsText.setMargins(0,8,0,0);
            txtView.setLayoutParams(paramsText);
            linearLayBestOption.addView(txtView,3);
        }
//        ImageView bestOptionImageView = findViewById(R.id.bestOptionImage);
//        bestOptionImageView.setImageResource(R.drawable.geforce2080);

        TextView compareTitleView = findViewById(R.id.titleCompare);
        TextView compareNameView = findViewById(R.id.compareName);
        TextView comparePriceView = findViewById(R.id.comparePrice);
        TextView compareQuantityView = findViewById(R.id.compareQuantity);

        if(predictor.bestSolution().getName().equals(predictor.predictCloud().getName())){
            compareTitleView.setText("Comparado ao servidor fisico:");
            compareNameView.setText("Nome: "+predictor.predictServer().getName());
            comparePriceView.setText("Preço: R$ "+String.valueOf(predictor.predictServer().getPrice()));
            compareQuantityView.setText("Quantidade: "+predictor.predictServer().getQuantity());

        }
        else{
            compareTitleView.setText("Comparado ao cloud:");
            compareNameView.setText("Nome: "+predictor.predictCloud().getName());
            comparePriceView.setText("Preço: R$ "+String.valueOf(predictor.predictCloud().getPrice()));
            compareQuantityView.setText("Quantidade: "+String.valueOf(predictor.predictCloud().getQuantity()));
        }
        TextView maisDetalhes = findViewById(R.id.maisDetalhes);
        linearLayBestOption.setOnClickListener((view) -> {
            System.out.println(linearLayBestOption.getChildCount());
            if(linearLayBestOption.getChildCount() < 6){
                if(predictor.bestSolution().equals(predictor.predictServer())) {
                    TextView txtView = new TextView(getBaseContext());
                    txtView.setText("Memoria de vídeo: " + predictor.bestSolution().getGpuMemory() + " GB");
                    txtView.setTextColor(Color.parseColor("#ffffff"));
                    txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText.setMargins(0,20,0,0);
                    txtView.setLayoutParams(paramsText);
                    linearLayBestOption.addView(txtView);
                    maisDetalhes.setText("-");
                    //GPUNumber
                    TextView txtView4 = new TextView(getBaseContext());
                    txtView4.setText("Quantidade de GPUs: "+predictor.predictServer().getGpuNumber());
                    txtView4.setTextColor(Color.parseColor("#ffffff"));
                    txtView4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText4.setMargins(0,10,0,0);
                    txtView4.setLayoutParams(paramsText4);
                    linearLayBestOption.addView(txtView4);
                }
                else{
                    //GPUNumber
                    TextView txtView4 = new TextView(getBaseContext());
                    txtView4.setText("Quantidade de GPUs: "+predictor.predictCloud().getGpuNumber());
                    txtView4.setTextColor(Color.parseColor("#ffffff"));
                    txtView4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText4.setMargins(0,15,0,0);
                    txtView4.setLayoutParams(paramsText4);
                    linearLayBestOption.addView(txtView4);
                    //Ram
                    TextView txtView3 = new TextView(getBaseContext());
                    txtView3.setText("RAM: "+predictor.predictCloud().getRam()+" GB");
                    txtView3.setTextColor(Color.parseColor("#ffffff"));
                    txtView3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText3.setMargins(0,10,0,0);
                    txtView3.setLayoutParams(paramsText3);
                    linearLayBestOption.addView(txtView3);
                    //Memory
                    TextView txtView = new TextView(getBaseContext());
                    txtView.setText("Memoria de vídeo: " + predictor.bestSolution().getGpuMemory() + " GB");
                    txtView.setTextColor(Color.parseColor("#ffffff"));
                    txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText.setMargins(0,10,0,0);
                    txtView.setLayoutParams(paramsText);
                    linearLayBestOption.addView(txtView);
                    maisDetalhes.setText("-");
                    //NVLink
                    TextView txtView2 = new TextView(getBaseContext());
                    if(predictor.predictCloud().nvLink){ txtView2.setText("Contém NVLink: Sim"); }
                    else{ txtView2.setText("Contém NVLink: Não");}
                    txtView2.setTextColor(Color.parseColor("#ffffff"));
                    txtView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText2.setMargins(0,10,0,0);
                    txtView2.setLayoutParams(paramsText2);
                    linearLayBestOption.addView(txtView2);

                }
            }
            else{
                if(predictor.bestSolution().equals(predictor.predictServer())) {
                    linearLayBestOption.removeViewAt(linearLayBestOption.getChildCount() - 1);
                    linearLayBestOption.removeViewAt(linearLayBestOption.getChildCount() - 1);
                    maisDetalhes.setText("+");
                }
                else {
                    for(int i=4;i>0;i--){
                        System.out.println(i);
                        linearLayBestOption.removeViewAt(linearLayBestOption.getChildCount()-1);
                        maisDetalhes.setText("+");
                    }
                }
            }
        });

        LinearLayout linearLayoutSecondOption = findViewById(R.id.linearLay2);
        TextView maisDetalhes2 = findViewById(R.id.maisDetalhes2);
        linearLayoutSecondOption.setOnClickListener((view) -> {
            if(linearLayoutSecondOption.getChildCount() == 4){
                if(predictor.bestSolution().equals(predictor.predictCloud())) {
                    TextView txtView = new TextView(getBaseContext());
                    txtView.setText("Memoria de vídeo: " + predictor.predictServer().getGpuMemory() + " GB");
                    txtView.setTextColor(Color.parseColor("#ffffff"));
                    txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText.setMargins(0,15,0,0);
                    txtView.setLayoutParams(paramsText);
                    linearLayoutSecondOption.addView(txtView);
                    maisDetalhes2.setText("-");
                    //GPUNumber
                    TextView txtView4 = new TextView(getBaseContext());
                    txtView4.setText("Quantidade de GPUs: "+predictor.predictServer().getGpuNumber());
                    txtView4.setTextColor(Color.parseColor("#ffffff"));
                    txtView4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText4.setMargins(0,10,0,0);
                    txtView4.setLayoutParams(paramsText4);
                    linearLayoutSecondOption.addView(txtView4);
                }
                else{
                    //GPUNumber
                    TextView txtView4 = new TextView(getBaseContext());
                    txtView4.setText("Quantidade de GPUs: "+predictor.predictCloud().getGpuNumber());
                    txtView4.setTextColor(Color.parseColor("#ffffff"));
                    txtView4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText4.setMargins(0,15,0,0);
                    txtView4.setLayoutParams(paramsText4);
                    linearLayoutSecondOption.addView(txtView4);
                    //Ram
                    TextView txtView3 = new TextView(getBaseContext());
                    txtView3.setText("RAM: "+predictor.predictCloud().getRam()+" GB");
                    txtView3.setTextColor(Color.parseColor("#ffffff"));
                    txtView3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText3.setMargins(0,10,0,0);
                    txtView3.setLayoutParams(paramsText3);
                    linearLayoutSecondOption.addView(txtView3);
                    //Memory
                    TextView txtView = new TextView(getBaseContext());
                    txtView.setText("Memoria de vídeo: " + predictor.predictCloud().getGpuMemory() + " GB");
                    txtView.setTextColor(Color.parseColor("#ffffff"));
                    txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText.setMargins(0,10,0,0);
                    txtView.setLayoutParams(paramsText);
                    linearLayoutSecondOption.addView(txtView);
                    maisDetalhes2.setText("-");
                    //NVLink
                    TextView txtView2 = new TextView(getBaseContext());
                    if(predictor.predictCloud().nvLink){ txtView2.setText("Contém NVLink: Sim"); }
                    else{ txtView2.setText("Contém NVLink: Não");}
                    txtView2.setTextColor(Color.parseColor("#ffffff"));
                    txtView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    LinearLayout.LayoutParams paramsText2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    paramsText4.setMargins(0,10,0,0);
                    txtView2.setLayoutParams(paramsText2);
                    linearLayoutSecondOption.addView(txtView2);

                }
            }
            else{
                if(predictor.bestSolution().equals(predictor.predictCloud())) {
                    linearLayoutSecondOption.removeViewAt(linearLayoutSecondOption.getChildCount() - 1);
                    linearLayoutSecondOption.removeViewAt(linearLayoutSecondOption.getChildCount() - 1);
                    maisDetalhes2.setText("+");
                }
                else {
                    for(int i=4;i>0;i--){
                        System.out.println(i);
                        linearLayoutSecondOption.removeViewAt(linearLayoutSecondOption.getChildCount()-1);
                        maisDetalhes2.setText("+");
                    }
                }
            }
        });



    }

}