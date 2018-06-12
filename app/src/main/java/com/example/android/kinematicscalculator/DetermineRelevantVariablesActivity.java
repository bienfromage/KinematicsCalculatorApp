package com.example.android.kinematicscalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class DetermineRelevantVariablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determine_relevant_variables);

        Button nextButton = findViewById(R.id.nextRelevantVariablesButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //determine which variables the app needs to get data from
                CheckBox checkBox = findViewById(R.id.vo);
                if(checkBox.isChecked())
                    CalculatorData.getKinematicVariable("vo").setGetValue(true);
                checkBox = findViewById(R.id.v);
                if(checkBox.isChecked())
                    CalculatorData.getKinematicVariable("v").setGetValue(true);
                checkBox = findViewById(R.id.a);
                if(checkBox.isChecked())
                    CalculatorData.getKinematicVariable("a").setGetValue(true);
                checkBox = findViewById(R.id.t);
                if(checkBox.isChecked())
                    CalculatorData.getKinematicVariable("t").setGetValue(true);
                checkBox = findViewById(R.id.x);
                if(checkBox.isChecked())
                    CalculatorData.getKinematicVariable("s").setGetValue(true);

                if(CalculatorData.sum() < 3){
                    Toast.makeText(DetermineRelevantVariablesActivity.this, "Whoops! Looks like you enterd fewer than three variables. You must enter at least three variables for the calculator to work.", Toast.LENGTH_LONG).show();
                }else{
                    if(CalculatorData.getEntryType() == 0)
                        startActivity(new Intent(DetermineRelevantVariablesActivity.this, InputActivity.class));
                    else
                        startActivity(new Intent(DetermineRelevantVariablesActivity.this, InputAngleActivity.class));
                }
            }
        });
    }
}
