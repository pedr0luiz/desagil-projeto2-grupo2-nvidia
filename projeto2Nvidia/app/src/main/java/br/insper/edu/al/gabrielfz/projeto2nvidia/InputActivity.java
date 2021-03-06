package br.insper.edu.al.gabrielfz.projeto2nvidia;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

public class InputActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int databaseSize = 0;
    private int teamSize = 0;
    private int projects = 0;
    private String usageType;
    private String projectType;
    private int projectTime = 0;
    private int applications = 0;
    private boolean inferencia;
    private DollarGetter dollar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dollar = new DollarGetter();
        dollar.execute();

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
        spinner5.setOnItemSelectedListener(this);


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
            if(inferencia){
                try{
                    int h = 1;
                    EditText applicationsEdit = findViewById(h);
                    this.applications = Integer.parseInt(applicationsEdit.getText().toString());
                } catch (NumberFormatException e){
                    this.applications = 0;
                } catch (NullPointerException e) {
                    this.applications = 0;
                }
            }
            this.databaseSize = Integer.parseInt(data[0].split(" ")[0]);
            this.projects = Integer.parseInt(data[1].split(" ")[0]);
            this.projectType = data[2];
            this.usageType = data[3];


            if (dollar.isDone()) {
                Intent intent = new Intent(this,ResultsActivity.class);
                intent.putExtra("projectTime",this.projectTime);
                intent.putExtra("applications",this.applications);
                intent.putExtra("teamSize",this.teamSize);
                intent.putExtra("projectType",this.projectType);
                intent.putExtra("projects",this.projects);
                intent.putExtra("databaseSize",this.databaseSize);
                intent.putExtra("usageType",this.usageType);
                intent.putExtra("dollarValue",dollar.dollar);
                intent.putExtra("caller","InputActivity");
//                intent.putExtra("dollar", dollar);
                startActivity(intent);
            }

        });

    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        System.out.println(pos);
        if(pos == 1){
            RelativeLayout RelLay = findViewById(R.id.scrollRelLay);
            LinearLayout linearLay7 = new LinearLayout(getBaseContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(30,40,30,40);
            params.addRule(RelativeLayout.BELOW, R.id.LinearLayInput6);
            linearLay7.setLayoutParams(params);
            linearLay7.setBackgroundResource(R.drawable.roundborders);
            linearLay7.setOrientation(LinearLayout.VERTICAL);
            RelLay.addView(linearLay7);
            // Add Label
            TextView aplicationsLabel = new TextView(getBaseContext());
            LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            aplicationsLabel.setTextSize(18);
            aplicationsLabel.setTypeface(aplicationsLabel.getTypeface(), Typeface.BOLD);
            aplicationsLabel.setTextColor(Color.parseColor("#ffffff"));
            aplicationsLabel.setLayoutParams(paramsText);
            aplicationsLabel.setText("Aplicações Simultâneas:");
            linearLay7.addView(aplicationsLabel);
            //Add edit Text
            EditText aplicationsEdit = new EditText(getBaseContext());
            LinearLayout.LayoutParams paramsEdit = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            aplicationsEdit.setTextSize(15);
            aplicationsEdit.setLayoutParams(paramsEdit);
            aplicationsEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            int i = 1;
            aplicationsEdit.setId(i);
            linearLay7.addView(aplicationsEdit);
            this.inferencia = true;
        }
        else{
            RelativeLayout relativeLayout = findViewById(R.id.scrollRelLay);
            if (relativeLayout.getChildCount() > 6) {
                relativeLayout.removeViewAt(relativeLayout.getChildCount() - 1);
                this.inferencia = false;
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    }

