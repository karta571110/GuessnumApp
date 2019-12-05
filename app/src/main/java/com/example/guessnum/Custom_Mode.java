package com.example.guessnum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Custom_Mode extends AppCompatActivity {

    private EditText customrlueEdittxt, guessEdittxt;
    private Button startbtn, guessbtn, showAnswerbtn, restartbtn;
    private TextView guessProcesstxt, guessTimetxt;
    private ListView historylistview;
    private ListAdapter laSimple;
    private ArrayList<String> listItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__mode);
        Findviews();
        InitialSetup();
        SetButttonlistener();
    }

    private void Findviews() {
        customrlueEdittxt = (EditText) findViewById(R.id.customRuleEditText);//玩家自訂規則之輸入欄
        guessEdittxt = (EditText) findViewById(R.id.GuessEditText);//玩家輸入答案之輸入欄
        startbtn = (Button) findViewById(R.id.StartBtn);//開始按鈕
        guessbtn = (Button) findViewById(R.id.GuessBtn);//猜數字按鈕
        showAnswerbtn = (Button) findViewById(R.id.ShowAnswer);//顯示答案
        restartbtn = (Button) findViewById(R.id.RestartBtn);//重新開始按鈕
        guessProcesstxt = (TextView) findViewById(R.id.GuessProcess);//上一次輸入的數字為?A?B
        guessTimetxt = (TextView) findViewById(R.id.GuessTime);//猜測次數
        historylistview = (ListView) findViewById(R.id.historyListView);//歷史猜測紀錄

    }

    private void InitialSetup() {
        // 設定 customrlueEdittxt,guessEdittxt 屬性

        customrlueEdittxt.setTextColor(Color.BLUE);
        customrlueEdittxt.requestFocus();
        guessEdittxt.setEnabled(false);

        //設定 guessbtn, showAnswerbtn
        guessbtn.setEnabled(false);
        showAnswerbtn.setEnabled(false);

        //清除ListView資料
        ClearResult();

        // 隱藏 SoftKeyboard
        HideSoftKeyboard();

    }

    private void ClearResult() {
        //清除listItems的所有資料
        listItems.clear();
        laSimple = new ArrayAdapter<String>(this, R.layout.my_list_row, listItems);
        historylistview.setAdapter(laSimple);

        //清除訊息區
        guessProcesstxt.setText("");

        //清除紀錄
        guessTimetxt.setText("0次");
    }

    private void HideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().peekDecorView().getWindowToken(), 0);
    }
private void SetButttonlistener(){

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customrlueEdittxt.length() !=1 || customrlueEdittxt.length()>1){  // 沒輸入猜幾位數時，按鈕無效
                    Toast.makeText(Custom_Mode.this,"您未輸入或者是輸入的位數過多",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
}
}

