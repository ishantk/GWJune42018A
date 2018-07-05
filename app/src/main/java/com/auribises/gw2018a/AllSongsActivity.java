package com.auribises.gw2018a;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class AllSongsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    String sdCardPath;

    void initViews(){
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        /*adapter.add("SongA.mp3"); //0 th index
        adapter.add("SongB.mp3");
        adapter.add("SongC.mp3");
        adapter.add("SongD.mp3");
        adapter.add("SongE.mp3"); // n-1 index*/


        sdCardPath = Environment.getExternalStorageDirectory().getPath();

        File file = new File(sdCardPath);
        String[] names = file.list();

        File[] files = file.listFiles();

        for(File f : files){
            if(f.isDirectory()){
                //.....
            }
        }

        for(String str : names){

            if(str.endsWith(".mp3"))
                adapter.add(str);

        }


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);

        getSupportActionBar().setTitle("All Songs");

        initViews();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String songName = adapter.getItem(position);
        Toast.makeText(this,"You Selected: "+songName,Toast.LENGTH_LONG).show();
    }
}
