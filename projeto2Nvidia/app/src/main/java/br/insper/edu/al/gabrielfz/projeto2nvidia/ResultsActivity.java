package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ImageView imgNavBar = findViewById(R.id.imageNavBar);
        imgNavBar.setImageResource(R.drawable.horizontallogo);

        TextView bestOptionView = findViewById(R.id.bestOption);
        bestOptionView.setText(getIntent().getStringExtra("bestOption"));
        TextView bestOptionPriceView = findViewById(R.id.bestOptionPrice);
        bestOptionPriceView.setText("Preço: "+ getIntent().getStringExtra("bestOptionPrice")+" USD");
        ImageView bestOptionImageView = findViewById(R.id.bestOptionImage);
        bestOptionImageView.setImageResource(R.drawable.geforce2080);

        TextView compareTitleView = findViewById(R.id.titleCompare);
        TextView compareNameView = findViewById(R.id.compareName);
        TextView comparePriceView = findViewById(R.id.comparePrice);

        if(getIntent().getStringExtra("bestCloudOption").equals(getIntent().getStringExtra("bestOption"))){
            compareTitleView.setText("Comparado ao servidor fisico:");
            compareNameView.setText(getIntent().getStringExtra("bestServerOption"));
            comparePriceView.setText(getIntent().getStringExtra("bestServerOptionPrice"));

        }
        else{
            compareTitleView.setText("Comparado ao cloud:");
            compareNameView.setText(getIntent().getStringExtra("bestCloudOption"));
            comparePriceView.setText(getIntent().getStringExtra("bestCloudOptionPrice"));
        }



    }
}
