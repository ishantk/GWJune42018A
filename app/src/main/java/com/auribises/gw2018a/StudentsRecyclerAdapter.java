package com.auribises.gw2018a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentsRecyclerAdapter extends RecyclerView.Adapter<StudentsRecyclerAdapter.ViewHolder>{


    Context context;
    int resource;
    ArrayList<Student> objects;

    public StudentsRecyclerAdapter(Context context, int resource, ArrayList<Student> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //2. Get Object from ArrayList
        Student student = objects.get(position);

        //3. Set the data on list_item
        holder.imageView.setBackgroundResource(student.image);
        holder.txtName.setText(student.name);
        holder.txtPhone.setText(student.phone);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtName;
        TextView txtPhone;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtPhone = itemView.findViewById(R.id.textViewPhone);

        }
    }

}
