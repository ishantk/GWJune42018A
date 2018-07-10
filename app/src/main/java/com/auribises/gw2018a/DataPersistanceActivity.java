package com.auribises.gw2018a;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class DataPersistanceActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtTitle;
    EditText eTxtName;
    Button btnSubmit;

    String name;

    SharedPreferences preferences;   // Creates and Reads Data in XML File
    SharedPreferences.Editor editor; // Writes Data in XML File

    void initViews(){
        txtTitle = findViewById(R.id.textViewTitle);
        eTxtName = findViewById(R.id.editTextName);
        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_persistance);
        initViews();

        //readDataFromInternalFile();
        //readDataFromExternalFile();

        // Application SandBox
        preferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = preferences.edit();


        String name = preferences.getString("keyName","NA");
        txtTitle.setText("Welcome, "+name);


    }

    void saveDataInInternalFile(){
        try {

            // /data/data.com.auribises.gw2018b/files/data.txt
            FileOutputStream outputStream = openFileOutput("data.txt", MODE_PRIVATE);
            //FileOutputStream outputStream = openFileOutput("data.txt", MODE_APPEND);
            outputStream.write(name.getBytes());
            outputStream.close();

            Toast.makeText(this,"Name Written Successfully !!",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromInternalFile(){
        try {

            // /data/data.com.auribises.gw2018b/files/data.txt  | SANDBOX Region of App
            FileInputStream inputStream = openFileInput("data.txt");

            //InputStreamReader reader = new InputStreamReader(inputStream);
            //BufferedReader buffer = new BufferedReader(reader);

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String line = buffer.readLine();

            txtTitle.setText("Welcome, "+line);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void saveDateInExternalFile(){
        try {

            // SD Card
            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            File file = new File(path);

            //FileOutputStream outputStream = new FileOutputStream(file,true); // Append Mode
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(name.getBytes());
            outputStream.close();

            Toast.makeText(this,"Name Written Successfully !!",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromExternalFile(){
        try {

            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            File file = new File(path);

            FileInputStream inputStream = new FileInputStream(file);

            //InputStreamReader reader = new InputStreamReader(inputStream);
            //BufferedReader buffer = new BufferedReader(reader);

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String line = buffer.readLine();

            txtTitle.setText("Welcome, "+line);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        name = eTxtName.getText().toString();
        //saveDataInInternalFile();
        //saveDateInExternalFile();

        editor.putString("keyName",name);

        // Save the data
        //editor.commit(); // Saves Data Sync

        editor.apply(); // Saves Data ASync (In the Background)

        Toast.makeText(this,"Name Written Successfully !!",Toast.LENGTH_LONG).show();
    }
}
