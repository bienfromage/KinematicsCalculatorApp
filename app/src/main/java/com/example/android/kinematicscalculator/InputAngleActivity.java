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

public class InputAngleActivity extends AppCompatActivity {
    private String currentInputKinematicVariable;
    private TextView requestText;
    private EditText inputBox;
    private EditText angleBox;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_angle);

        requestText = findViewById(R.id.enterValueTextViewAngle);
        inputBox = findViewById(R.id.inputBoxMagnitude);
        angleBox = findViewById(R.id.inputAngleBox);
        nextButton=findViewById(R.id.nextInputButtonAngle);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputBox.getText().toString();
                String angleInput = angleBox.getText().toString();

                if(input.equals(""))
                    Toast.makeText(InputAngleActivity.this,"Please input a value",Toast.LENGTH_SHORT).show();
                else if(angleInput.equals(""))
                    Toast.makeText(InputAngleActivity.this,"Please input an angle",Toast.LENGTH_SHORT).show();
                else {
                    Double value = Double.parseDouble(input);

                    Double angle = Double.parseDouble(angleInput);

                    KinematicVariable kinematicVariable =
                            CalculatorData.getKinematicVariable(currentInputKinematicVariable);
                    kinematicVariable.setValue(value);
                    ((KinematicVariable2D) kinematicVariable).setAngle(angle * Math.PI / 180);
                    kinematicVariable.setGetValue(false);
                    ((KinematicVariable2D) kinematicVariable).componentCalculate();
                    inputBox.setText("");
                    angleBox.setText("");
                    inputBox.requestFocus();

                    updateUI();
                }
            }
        });

        inputBox.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    angleBox.requestFocus();
                    return true;
                }
                return false;
            }
        });

        angleBox.setOnEditorActionListener(new EditText.OnEditorActionListener() {
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
            startActivity(new Intent(InputAngleActivity.this,OutputActivity.class));
        }else if(nextKey.equals("t")){
            startActivity(new Intent(this,InputActivity.class));
        }else{
            currentInputKinematicVariable = nextKey;
            requestText.setText("Enter the value of "+nextKey+" and its corresponding angle");
        }

        inputBox.requestFocus();
    }
}
