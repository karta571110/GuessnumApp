package com.example.guessnum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button eCMbtn=(Button)findViewById(R.id.EnterCustomModeBtn);
        Button ePMbtn=(Button)findViewById(R.id.EnterPlayBtn);

    }
}
