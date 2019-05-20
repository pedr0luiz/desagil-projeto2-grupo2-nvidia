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

        TextView cloudOptionView = findViewById(R.id.bestCloudOption);
        cloudOptionView.setText(getIntent().getStringExtra("bestCloudOption"));
        TextView cloudOptionPriceView = findViewById(R.id.bestCloudOptionPrice);
        cloudOptionPriceView.setText("Preço: "+ getIntent().getStringExtra("bestCloudOptionPrice")+" USD");

        TextView serverOptionView = findViewById(R.id.bestServerOption);
        serverOptionView.setText(getIntent().getStringExtra("bestServerOption"));
        TextView serverOptionViewPrice = findViewById(R.id.bestServerOptionPrice);
        serverOptionViewPrice.setText("Preço: "+ getIntent().getStringExtra("bestServerOptionPrice")+" USD");

    }
}
