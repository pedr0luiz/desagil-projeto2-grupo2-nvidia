package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load image
        ImageView imgViewMain = findViewById(R.id.imageViewMain);
        imgViewMain.setImageResource(R.drawable.logonvidiafinal);

        //Button Click
        Button botaoInit = findViewById(R.id.buttonInit);
        botaoInit.setOnClickListener((view) -> {
            Intent intent = new Intent(this,InputActivity.class);
            intent.putExtra("caller","MainActivity");
            startActivity(intent);
        });
    }

}
