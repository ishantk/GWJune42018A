package com.auribises.gw2018a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //System.out.println("MainActivity - onCreate");
        Log.i("MainActivity","--onCreate--");
    }

    public void clickHandler(View vRef){

        String dateTime = new Date().toString();

        Toast.makeText(this,"Its: "+dateTime,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //System.out.println("MainActivity - onStart");
        Log.i("MainActivity","--onStart--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //System.out.println("MainActivity - onResume");
        Log.i("MainActivity","--onResume--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //System.out.println("MainActivity - onPause");
        Log.i("MainActivity","--onPause--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //System.out.println("MainActivity - onStop");
        Log.i("MainActivity","--onStop--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //System.out.println("MainActivity - onDestroy");
        Log.i("MainActivity","--onDestroy--");
    }
}
