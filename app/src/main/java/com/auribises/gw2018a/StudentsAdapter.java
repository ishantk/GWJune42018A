package com.auribises.gw2018a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    // getView method will be executed from 0 to n-1 (position), where n is size of objects
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //1. Create a View which points to Layout i.e. list_item

        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtName = view.findViewById(R.id.textViewName);
        TextView txtPhone = view.findViewById(R.id.textViewPhone);

        //2. Get Object from ArrayList
        Student student = objects.get(position);

        //3. Set the data on list_item
        imageView.setBackgroundResource(student.image);
        txtName.setText(student.name);
        txtPhone.setText(student.phone);


        return view;
    }
}
