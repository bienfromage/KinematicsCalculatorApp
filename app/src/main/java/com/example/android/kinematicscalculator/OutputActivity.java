package com.example.android.kinematicscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    private TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        outputTextView = findViewById(R.id.output);
        int equationNumber = CalculatorData.chooseEquation();
        while(equationNumber != -1){
            CalculatorData.solve(equationNumber);
            equationNumber = CalculatorData.chooseEquation();
        }

        outputTextView.setText(CalculatorData.getOutput());
    }
}
