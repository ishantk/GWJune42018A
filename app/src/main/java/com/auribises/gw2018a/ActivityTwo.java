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

        // Receive the Intent from Calling Activity
        Intent rcv = getIntent();

        //String name = rcv.getStringExtra("keyName");
        //String email = rcv.getStringExtra("keyEmail");
        //int age = rcv.getIntExtra("keyAge",0);

        /*Bundle rcvBun = rcv.getBundleExtra("keyBundle");

        String name = rcvBun.getString("keyName");
        String email = rcvBun.getString("keyEmail");
        int age = rcvBun.getInt("keyAge",0);

        eTxtName.setText(name);
        eTxtEmail.setText(email+" - Age: "+age);
        */

        Person person = (Person) rcv.getSerializableExtra("keyPerson");
        eTxtName.setText(person.name);
        eTxtEmail.setText(person.email);



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
