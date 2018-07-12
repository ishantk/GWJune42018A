package com.auribises.gw2018a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllStudentsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //ListView listView;
    //GridView listView;

    RecyclerView recyclerView;

    ArrayList<Student> students;
    StudentsAdapter adapter;

    StudentsRecyclerAdapter recyclerAdapter;

    void initViews(){

        //listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);
        students = new ArrayList<>();

        Student s1 = new Student(R.drawable.category,"John","+91 99999 88888");
        Student s2 = new Student(R.drawable.pb,"Jennie","+91 88888 88888");
        Student s3 = new Student(R.drawable.contact,"Jim","+91 77777 99999");
        Student s4 = new Student(R.drawable.music,"Jack","+91 66666 66666");
        Student s5 = new Student(R.drawable.folder,"Joe","+91 55555 00000");

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);

        //adapter = new StudentsAdapter(this,R.layout.list_item,students);
        //listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);

        recyclerAdapter = new StudentsRecyclerAdapter(this,R.layout.list_item,students);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);


        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = students.get(position);
        Toast.makeText(this,"You Selected: "+student.name,Toast.LENGTH_LONG).show();
    }
}
