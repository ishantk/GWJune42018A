package com.auribises.gw2018a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AllStudentsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Student> students;
    StudentsAdapter adapter;

    void initViews(){

        listView = findViewById(R.id.listView);
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

        adapter = new StudentsAdapter(this,R.layout.list_item,students);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);
        initViews();
    }
}
