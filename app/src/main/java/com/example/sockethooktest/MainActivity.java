package com.example.sockethooktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sockethooktest.oldtest.SocketHook;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";
    public static Button btn_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_close = findViewById(R.id.btn_guanbi);
        Test t = new Test();
//        t.test2();
        try{
//            t.test1();
            SocketHook.startHook();
//            Socket.setSocketImplFactory(new MonitorSocketFactory(getApplicationContext()));

            Log.d(TAG,"Begin hook");
            t.test2();
//            SocketHook.stopHook();
//            Log.d(TAG,"Stop hook");


        } catch (IOException e){
            Log.d(TAG,"IOException occur");
        }
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SocketHook.stopHook();
                            Log.d(TAG,"Stop hook");

            }
        });
    }


}