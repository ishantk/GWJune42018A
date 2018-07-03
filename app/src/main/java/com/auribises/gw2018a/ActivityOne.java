package com.auribises.gw2018a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity {


    // Declare references for the Views, which you wish to use
    EditText eTxtName, eTxtEmail;
    Button btnSubmit;

    Person person;

    // Create a Method to initialize these views
    void initViews(){ // Any Name of your choice
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnSubmit = findViewById(R.id.buttonSubmit);

        person = new Person();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Binds the Layout on Activity. It should be the first statement after super call
        setContentView(R.layout.activity_one);
        initViews();
    }

    public void clickHandler(View view){


        person.name = eTxtName.getText().toString();
        person.email = eTxtEmail.getText().toString();

        Toast.makeText(this,person.toString(),Toast.LENGTH_LONG).show();

        //Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);
        //startActivity(intent);

    }
}
