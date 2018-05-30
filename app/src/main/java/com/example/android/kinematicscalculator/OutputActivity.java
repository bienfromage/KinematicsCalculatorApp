package com.example.android.kinematicscalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    private TextView outputTextView;
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalculatorData.initMap();
                startActivity(new Intent(OutputActivity.this,StartActivity.class));
            }
        });

        outputTextView = findViewById(R.id.output);
        int equationNumber = CalculatorData.chooseEquation();
        while(equationNumber != -1){
            CalculatorData.solve(equationNumber);
            equationNumber = CalculatorData.chooseEquation();
        }

        outputTextView.setText(CalculatorData.getOutput());
    }
}
