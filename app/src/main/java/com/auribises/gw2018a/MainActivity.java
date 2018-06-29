package com.auribises.gw2018a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickHandler(View vRef){

        String dateTime = new Date().toString();

        Toast.makeText(this,"Its: "+dateTime,Toast.LENGTH_LONG).show();
    }
}
