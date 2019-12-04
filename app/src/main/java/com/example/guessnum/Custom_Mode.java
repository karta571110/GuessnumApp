package com.example.guessnum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Custom_Mode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__mode);


    }

    private void findviews(){
        EditText cRetxt=(EditText)findViewById(R.id.customRuleEditText);//玩家自訂規則之輸入欄
        EditText gEetxt=(EditText)findViewById(R.id.GuessEditText);//玩家輸入答案之輸入欄
        Button startbtn=(Button)findViewById(R.id.StartBtn);//開始按鈕
        Button guessbtn=(Button)findViewById(R.id.GuessBtn);//猜數字按鈕
        Button sAbtn=(Button)findViewById(R.id.ShowAnswer);//顯示答案
        Button restartbtn=(Button)findViewById(R.id.RestartBtn);//重新開始按鈕
        TextView gPtxt=(TextView)findViewById(R.id.GuessProcess);//上一次輸入的數字為?A?B
        TextView gTtxt=(TextView)findViewById(R.id.GuessTime);//猜測次數
    }
}
