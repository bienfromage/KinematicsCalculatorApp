package com.example.android.kinematicscalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class StartActivity extends AppCompatActivity {
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //wait 1 second before switching screen (to display splash)
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            Log.e("SPLASH_ERROR","Problem with splash screen pause: "+e);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //set up hashmap to hold physics variables
        CalculatorData.initMap();

        //on start button press, open next screen
        startButton=findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartActivity.this, DetermineRelevantVariablesActivity.class);
                startActivity(i);
            }
        });
    }
}
