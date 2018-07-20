package com.auribises.gw2018a.ui;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.auribises.gw2018a.R;
import com.auribises.gw2018a.Util;
import com.auribises.gw2018a.adapter.UsersAdapter;
import com.auribises.gw2018a.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllUsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listView)
    ListView listView;
    ArrayAdapter<String> adapter;
    UsersAdapter usersAdapter;


    ArrayList<User> users;
    ArrayList<String> users1;

    ContentResolver resolver;

    int pos = 0;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        ButterKnife.bind(this);

        users = new ArrayList<>();
        users1 = new ArrayList<>();



        resolver = getContentResolver();

        queryUsersFromDB();
    }

    void queryUsersFromDB(){

        String[] projection = {Util.COL_ID,Util.COL_NAME,Util.COL_PHONE,Util.COL_EMAIL};
        Cursor cursor = resolver.query(Util.USER_URI,projection,null,null,null);

        if(cursor!=null){

            while (cursor.moveToNext()){
                User user = new User();

                user.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                user.name = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                user.phone = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));
                user.email = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));

                users.add(user);
                users1.add(user.toString());
            }

            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,users1);

            //usersAdapter = new UsersAdapter(this,R.layout.list_item,users);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }

    }

    void deleteUserFromDB(){

        String where = Util.COL_ID+" = "+user.id;
        int i = resolver.delete(Util.USER_URI,where,null);
        Toast.makeText(this,user.name+" deleted..."+i,Toast.LENGTH_LONG).show();

        users.remove(pos);
        users1.remove(pos);

        adapter.notifyDataSetChanged();

    }

    void askForDeletion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete: "+user.name);
        builder.setMessage("Are you Sure ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUserFromDB();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    void showDetails(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Details of: "+user.name);
        builder.setMessage(user.toString());
        builder.setPositiveButton("Done",null);
        builder.create().show();
    }

    void showOptions(){
        String[] items = {"View","Update","Delete","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){
                    case 0:
                        showDetails();
                        break;

                    case 1:
                        Intent intent = new Intent(AllUsersActivity.this,RegisterUserActivity.class);
                        intent.putExtra("keyUser",user);
                        startActivity(intent);
                        break;

                    case 2:
                        askForDeletion();
                        break;

                    case 3:
                        finish();
                        break;
                }

            }
        });
        builder.create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        pos = position;
        user = users.get(position);

        Toast.makeText(this,"You Selected: "+user.name,Toast.LENGTH_LONG).show();

        showOptions();
    }
}
