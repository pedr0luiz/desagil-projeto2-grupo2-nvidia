package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class InputActivity extends AppCompatActivity {
    private int databaseSize = 0;
    private int teamSize = 0;
    private int concurrentProjects = 0;
    private String projectType;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        //Set Options first spinner
        Spinner spinner = (Spinner) findViewById(R.id.databaseSizeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.databaseSizeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //Set Options Second Spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.teamSizeSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.teamSizeArray, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        //Set Options fo third spinner
        Spinner spinner3 = (Spinner) findViewById(R.id.projectsSpinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.CurrentProjectSizeArray, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        //Set Options fo fourth spinner
        Spinner spinner4 = (Spinner) findViewById(R.id.projectTypeSpinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.ProjectTypeArray, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        Spinner[] spinnerVector = new Spinner[4];
        spinnerVector[0] = spinner;
        spinnerVector[1] = spinner2;
        spinnerVector[2] = spinner3;
        spinnerVector[3] = spinner4;


        //Get Submit Button
        Button botaoSubmit = findViewById(R.id.submitButton);
        botaoSubmit.setOnClickListener((view) -> {
            String[] data = new String[4];
            int idx = 0;
            for(Spinner spin: spinnerVector){
                if(spin.getSelectedItemPosition() == 0){
                    data[idx] = null;
                }
                else {
                    data[idx] = spin.getSelectedItem().toString();
                }
                idx++;
            }
            this.databaseSize = Integer.parseInt(data[0].split(" ")[0]);
            this.teamSize = Integer.parseInt(data[1].split(" ")[0]);
            this.concurrentProjects = Integer.parseInt(data[2].split(" ")[0]);
            this.projectType = data[3];
            Predictor predicao = new Predictor(this.teamSize,this.projectType,this.concurrentProjects,this.databaseSize);
            String name = predicao.bestSolution().getName();
            String price = String.valueOf(predicao.bestSolution().getPrice());
            String memory = String.valueOf(predicao.bestSolution().getGpuMemory());
            Intent intent = new Intent(this,ResultsActivity.class);
            intent.putExtra("Name",name);
            intent.putExtra("Preco",price);
            intent.putExtra("GPUMemory",memory);
            intent.putExtra("caller","InputActivity");
            startActivity(intent);




        });

    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        String selected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    }

