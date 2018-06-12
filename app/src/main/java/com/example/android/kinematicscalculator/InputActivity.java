package com.example.android.kinematicscalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                String input = inputBox.getText().toString();

                if(input.equals("")){
                    Toast.makeText(InputActivity.this,"Please enter a value",Toast.LENGTH_SHORT).show();
                }else {
                    Double value = Double.parseDouble(input);
                    KinematicVariable kinematicVariable =
                            CalculatorData.getKinematicVariable(currentInputKinematicVariable);
                    kinematicVariable.setValue(value);
                    kinematicVariable.setGetValue(false);
                    kinematicVariable.setHasValue(true);
                    inputBox.setText("");
                    updateUI();
                }
            }
        });

        inputBox.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    nextButton.performClick();
                    return true;
                }
                return false;
            }
        });

        updateUI();
    }

    private void updateUI(){
        String nextKey = CalculatorData.getNextKey();
        if(nextKey == null){
            startActivity(new Intent(InputActivity.this,OutputActivity.class));
        }else if(CalculatorData.getEntryType() == CalculatorData.ONE_DIMENSIONAL || nextKey.equals("t")){
            currentInputKinematicVariable = nextKey;
            requestText.setText("Enter the value of "+nextKey);
        }else{
            startActivity(new Intent(this,InputAngleActivity.class));
        }
    }
}