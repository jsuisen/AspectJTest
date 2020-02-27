package com.gw.aspectjtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean flag = AOPLoginUtils.Login("张三","123456");
        try{
            throw new IOException();
        }catch (IOException ex){
            Log.i("xhq","throw exception");
        }
        Log.i("OOP","test-"+flag);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
