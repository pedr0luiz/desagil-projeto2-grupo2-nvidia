package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView txtView = findViewById(R.id.resultsText);
        CharSequence str = getIntent().getStringExtra("Name");
        txtView.setText("Melhor Opção:" + str);
        TextView txtViewPrice = findViewById(R.id.priceText);
        CharSequence preco = getIntent().getStringExtra("Price");
        txtViewPrice.setText("Preço Final: " +preco);

    }
}
