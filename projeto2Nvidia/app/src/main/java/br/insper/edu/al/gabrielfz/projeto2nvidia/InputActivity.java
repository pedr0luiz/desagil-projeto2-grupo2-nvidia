package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InputActivity extends AppCompatActivity {
    private int databaseSize = 0;
    private int teamSize = 0;
    private int concurrentProjects = 0;
    private String usageType;
    private String projectType;
    private int projectTime = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        //Set Options first spinner
        Spinner spinner = (Spinner) findViewById(R.id.databaseSizeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.databaseSizeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


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

        //Set Options fo fifth spinner
        Spinner spinner5 = (Spinner) findViewById(R.id.typeOfUsageSpinner);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.usageTypeArray, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        Spinner[] spinnerVector = new Spinner[4];
        spinnerVector[0] = spinner;
        spinnerVector[1] = spinner3;
        spinnerVector[2] = spinner4;
        spinnerVector[3] = spinner5;
        EditText teamSizeEdit = findViewById(R.id.editTeamSize);
        EditText timeEdit = findViewById(R.id.editTime);


        //Get Submit Button
        Button botaoSubmit = findViewById(R.id.submitButton);
        botaoSubmit.setOnClickListener((view) -> {
            String[] data = new String[5];
            int idx = 0;
            for(Spinner spin: spinnerVector){
                data[idx] = spin.getSelectedItem().toString();
                idx++;
            }
            try{
                this.teamSize = Integer.parseInt(teamSizeEdit.getText().toString());
            } catch(NumberFormatException e){
                this.teamSize = 1;
            } catch (NullPointerException e){
                this.teamSize = 1;
            }
            try{
                this.projectTime = Integer.parseInt(timeEdit.getText().toString());
            } catch(NumberFormatException e){
                this.projectTime = 1;
            } catch(NullPointerException e){
                this.projectTime = 1;
            }
            this.databaseSize = Integer.parseInt(data[0].split(" ")[0]);
            this.concurrentProjects = Integer.parseInt(data[1].split(" ")[0]);
            this.projectType = data[2];
            this.usageType = data[3];
            Predictor predicao = new Predictor(this.teamSize,this.projectType,this.concurrentProjects,this.databaseSize,this.usageType);
            String bestOptionName = predicao.bestSolution().getName();
            String bestOptionPrice = String.valueOf(predicao.bestSolution().getPrice());

            String bestCloudOption = predicao.predictCloud().getName();
            String bestCloudOptionPrice = String.valueOf(predicao.predictCloudPrice());

            String bestServerOption = predicao.predictServer().getName();
            String bestServerOptionPrice = String.valueOf(predicao.predictServer().getPrice());

            Intent intent = new Intent(this,ResultsActivity.class);
            intent.putExtra("bestOption",bestOptionName);
            intent.putExtra("bestOptionPrice",bestOptionPrice);
            intent.putExtra("bestCloudOption", bestCloudOption);
            intent.putExtra("bestCloudOptionPrice",bestCloudOptionPrice);
            intent.putExtra("bestServerOption",bestServerOption);
            intent.putExtra("bestServerOptionPrice",bestServerOptionPrice);
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

