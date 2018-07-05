package com.auribises.gw2018a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        getSupportActionBar().setTitle("ActivityOne");
    }

    public void clickHandler(View view){


        //person.name = eTxtName.getText().toString();
        //person.email = eTxtEmail.getText().toString();

        //Toast.makeText(this,person.toString(),Toast.LENGTH_LONG).show();

        // To start a new Activity
        //Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);

        //1. FP1
        //intent.putExtra("keyName",person.name);
        //intent.putExtra("keyEmail",person.email);
        //intent.putExtra("keyAge",30);

        //2. FP2
        /*Bundle bundle = new Bundle();
        bundle.putString("keyName",person.name);
        bundle.putString("keyEmail",person.email);
        bundle.putInt("keyAge",30);

        intent.putExtra("keyBundle",bundle);*/

        //3. FP3 | Object shall be Serialized
        //intent.putExtra("keyPerson",person);

        //startActivity(intent);

        Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);
        startActivityForResult(intent,101); // Backward Passing

    }

    // Executed When ActivityTwo will setResult and finish
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 101 && resultCode == 201){

            String name = data.getStringExtra("keyName");
            String email = data.getStringExtra("keyEmail");
            eTxtName.setText(name);
            eTxtEmail.setText(email);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Explicit Way
        menu.add(1,101,1,"All Songs");
        menu.add(1,201,1,"Artists");
        menu.add(1,301,1,"Favorites");
        menu.add(1,401,1,"Albums");
        menu.add(1,501,1,"Recently Played");

        //Implicit Way
        //getMenuInflater().inflate(R.menu.menu_activityone,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case 101:
                Toast.makeText(this,"You Selected All Songs",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ActivityOne.this,AllSongsActivity.class);
                startActivity(intent);

                break;

            case 201:

                break;

            case 301:

                break;

            case 401:

                break;

            case 501:

                break;

            case R.id.aaj:
                Toast.makeText(this,"You Selected Aaj Tak",Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(ActivityOne.this,NewsActivity.class);
                startActivity(intent1);

                break;

            case R.id.bbc:

                break;

            //....
        }

        return super.onOptionsItemSelected(item);
    }
}
