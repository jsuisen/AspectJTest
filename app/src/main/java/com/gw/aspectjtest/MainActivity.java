package com.gw.aspectjtest;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("xhq", "MainActivity onCreate start");

        {
            boolean flag = Login("张三", "123456");
            try {
                throw new IOException();
            } catch (IOException ex) {
                Log.i("xhq", "throw exception in");
                ex.printStackTrace();
                Log.i("xhq", "throw exception out");
            }
            Log.i("xhq", "MainActivity onCreate start,flag:" + flag);
        }
        {
            boolean flag = Login("李四", "999999");
            try {
                throw new IOException();
            } catch (IOException ex) {
                Log.i("xhq", "throw exception in");
                ex.printStackTrace();
                Log.i("xhq", "throw exception out");
            }
            Log.i("xhq", "MainActivity onCreate start,flag:" + flag);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @TimeTrace(value = "登录")
    public static boolean Login(String userName, String passWord) {
        try {
            Log.i("xhq", "Login sleep start");
            Thread.sleep(2000);
            Log.i("xhq", "Login sleep end");
        } catch (InterruptedException e) {
            Log.i("xhq", "Login exception in");
            e.printStackTrace();
            Log.i("xhq", "Login exception out");
        }
        if ("张三".equals(userName) && "123456".equals(passWord)) {
            return true;
        } else {
            return false;
        }
    }
}
