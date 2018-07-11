package com.auribises.gw2018a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/*class Point{

    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

}*/

public class StudentsAdapter extends ArrayAdapter<Student>{

    Context context;
    int resource;
    ArrayList<Student> objects;

    public StudentsAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
}
