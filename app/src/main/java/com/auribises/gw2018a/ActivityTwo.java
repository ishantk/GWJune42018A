package com.auribises.gw2018a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity {

    // Declare references for the Views, which you wish to use
    EditText eTxtName, eTxtEmail;
    Button btnSubmit;

    // Create a Method to initialize these views
    void initViews(){ // Any Name of your choice
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnSubmit = findViewById(R.id.buttonSubmit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initViews();
    }

    public void clickHandler(View view){
        finish();
    }
}
