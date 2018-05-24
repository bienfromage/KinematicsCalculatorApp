package com.example.android.kinematicscalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;

public class InputActivity extends AppCompatActivity {
    private String currentInputKinematicVariable;
    private TextView requestText;
    private EditText inputBox;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        requestText = findViewById(R.id.enterValueTextView);
        inputBox = findViewById(R.id.inputBox);
        nextButton = findViewById(R.id.nextInputButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double value = Double.parseDouble(inputBox.getText().toString());

                KinematicVariable kinematicVariable =
                        CalculatorData.getKinematicVariable(currentInputKinematicVariable);
                kinematicVariable.setValue(value);
                kinematicVariable.setGetValue(false);
                kinematicVariable.setHasValue(true);
                updateUI();
            }
        });
        updateUI();
    }

    private void updateUI(){
        String nextKey = CalculatorData.getNextKey();
        if(nextKey == null){
            startActivity(new Intent(InputActivity.this,OutputActivity.class));
        }else{
            currentInputKinematicVariable = nextKey;
            requestText.setText("Enter the value of "+nextKey);
        }
    }
}
