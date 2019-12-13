package com.example.guessnum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.Random;

public class Play extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(Play.this, MainActivity.class);
        if (item.getItemId() == R.id.setting) {
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private EditText guessEdittxt;
    private Button guessbtn, showAnswerbtn, restartbtn;
    private TextView leveltxt, guessProcesstxt, guessTimetxt;
    private ListView historylistview;
    private ListAdapter laSimple;
    private ArrayList<String> listItems = new ArrayList<String>();
    private Boolean bingoFlag;
    private int playDigits = 1, n, m, totalGuessTime = 0;
    private String strAnswer, strOutputString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Findviews();
        InitialSetup();
        SetButttonlistener();
        SetAnswer();
    }

    private void Findviews() {
        leveltxt = (TextView) findViewById(R.id.Leveltxtplay); //關卡
        guessEdittxt = (EditText) findViewById(R.id.GuessEditTextplay);//玩家輸入答案之輸入欄
        guessbtn = (Button) findViewById(R.id.GuessBtnplay);//猜數字按鈕
        showAnswerbtn = (Button) findViewById(R.id.ShowAnswerplay);//顯示答案
        restartbtn = (Button) findViewById(R.id.RestartBtnplay);//重新開始按鈕
        guessProcesstxt = (TextView) findViewById(R.id.GuessProcessplay);//上一次輸入的數字為?A?B
        guessTimetxt = (TextView) findViewById(R.id.GuessTimeplay);//猜測次數
        historylistview = (ListView) findViewById(R.id.history);//歷史猜測紀錄

    }

    private void InitialSetup() {

        //設定leveltxt
        leveltxt.setText("第" + playDigits + "關");

        // 設定 guessEdittxt 屬性
        guessEdittxt.setHint("請猜" + playDigits + "位數的數字");

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


        //清除紀錄
        guessTimetxt.setText("0次");
    }

    private void HideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().peekDecorView().getWindowToken(), 0);
    }

    private void SetButttonlistener() {


        restartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playDigits = 1;
                Toast.makeText(Play.this, "回到第一關", Toast.LENGTH_LONG).show();
                //設定rstartbtn
                leveltxt.setText("第" + playDigits + "關");
                guessEdittxt.setHint("請猜" + playDigits + "位數的數字");
                SetAnswer();
                guessProcesstxt.setText("");
                // 清除 ListView 資料
                ClearResults();
            }
        });
        guessbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guessEdittxt.getText().toString().length() == playDigits)
                //輸入數字正確時
                {
                    //檢查猜對幾個數字
                    CheckMatchResult();
                    // 顯示猜數結果
                    ShowMatchResult();
                } else {
                    Toast.makeText(Play.this, "您輸入的位數過多或過少", Toast.LENGTH_LONG).show();
                }
            }
        });
        showAnswerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //顯示答案
                guessProcesstxt.setText("答案是  " + strAnswer);
                Toast.makeText(Play.this, " 您止步於" + playDigits + "關，答案已重置", Toast.LENGTH_LONG).show();

                //設定guessEdittxt屬性

                guessEdittxt.clearFocus();
                totalGuessTime=0;
                ClearResult();
                SetAnswer();


                // 隱藏 SoftKeyboard
                HideSoftKeyboard();
            }
        });


    }

    private void SetAnswer() {

        // 產生一組 playDigits 位數的數字，並且將產生的數寀儲放於 strAnswer
        strAnswer = "";
        for (int i = 1; i <= playDigits; i++) {
            int randomNo;
            boolean exitFlag;
            do {
                exitFlag = true;
                Random generator = new Random(System.currentTimeMillis());
                // 產生 1~9 之間的一個亂數整數
                do {
                    randomNo = generator.nextInt(10);
                } while (randomNo == 0);
                // 檢查這一個亂數整數與前面已產生的亂數整數是否重複
                if (i > 1) {
                    for (int j = 0; j <= (i - 2); j++) {
                        String chkDigiNo = strAnswer.substring(j, j + 1);
                        if (chkDigiNo.equals(String.valueOf(randomNo))) {
                            exitFlag = false;
                        }
                    }
                }
            } while (i > 0 && (!exitFlag));
            // 依序組合系統自動所產生的亂數整數
            if (i == 1) {
                strAnswer = String.valueOf(randomNo);
            } else {
                strAnswer += String.valueOf(randomNo);

            }
        }
    }

    private void ClearResults() {
        // 清除 listItems 的所有資料
        listItems.clear();
        laSimple = new ArrayAdapter<String>(this, R.layout.my_list_row, listItems);
        historylistview.setAdapter(laSimple);
        //清除訊息區
        totalGuessTime = 0;
        guessTimetxt.setText(totalGuessTime + "");


    }

    private void CheckMatchResult() {
        // 檢查猜中幾個數字，傳回  n A , m B 結果
        n = 0;
        m = 0;
        String strInputNumber = guessEdittxt.getText().toString();
        for (int i = 0; i < playDigits; i++) {
            String strInputDigit = strInputNumber.substring(i, i + 1);
            for (int j = 0; j < playDigits; j++) {
                String strAnswerDigit = strAnswer.substring(j, j + 1);
                if (strAnswerDigit.equals(strInputDigit)) {
                    if (i == j) { // 數字與位置都正確時，累計 n 值
                        n++;
                    } else {// 數字正確但位置不對時，累計 m 值
                        m++;
                    }
                }
            }
        }
        // 將猜數字結果放入 strOutputString
        if (n == playDigits) {
            //BINGO
            bingoFlag = true;
            strOutputString = strInputNumber + "   " + "BINGO!!";
        } else {
            //nAmB
            bingoFlag = false;
            strOutputString = strInputNumber + "    " + n + " A " + m + " B ";

        }
//將 strOutputString 放入 ListView 的 listItems
        listItems.add(strOutputString);
    }

    private void ShowMatchResult() {
        // 顯示 listItems 的所有資料
        laSimple = new ArrayAdapter<String>(this,
                R.layout.my_list_row,
                listItems);
        historylistview.setAdapter(laSimple);
        //顯示訊息區
        guessProcesstxt.setText(strOutputString);
        //顯示猜測次數
        totalGuessTime++;
        guessTimetxt.setText(totalGuessTime + "次");
        //清除guessEdittxt欄位
        guessEdittxt.setText("");
        if (bingoFlag)//BINGO時
        {
            playDigits++;
            //leveltxt
            leveltxt.setText("第" + playDigits + "關");
            // 設定  guessEdittxt 屬性
            guessEdittxt.setHint("請猜" + playDigits + "位數的數字");


            //總共猜幾次
            guessProcesstxt.setText("您總共猜了" + totalGuessTime + "次 ");

            Toast.makeText(Play.this, "進入下一關", Toast.LENGTH_LONG).show();
            totalGuessTime = 0;
            ClearResult();
            guessTimetxt.setText("上一題答案是 "+strAnswer);
            SetAnswer();
        }
        // 隱藏 SoftKeyboard
        HideSoftKeyboard();
    }
}
