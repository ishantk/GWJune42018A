package com.auribises.gw2018a.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.gw2018a.R;
import com.auribises.gw2018a.Util;
import com.auribises.gw2018a.model.User;


import butterknife.BindView;
import butterknife.ButterKnife;

/*
Ishants-Macbook-Air:~ ishantkumar$ cd /Users/ishantkumar/Library/Android/sdk
Ishants-Macbook-Air:sdk ishantkumar$ pwd
/Users/ishantkumar/Library/Android/sdk
Ishants-Macbook-Air:sdk ishantkumar$ cd platform-tools/
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb shell
generic_x86:/ # cd data/data/com.auribises.gw2018a
generic_x86:/data/data/com.auribises.gw2018a # pwd
/data/data/com.auribises.gw2018a
generic_x86:/data/data/com.auribises.gw2018a # ls
app_download_internal app_webview code_cache shared_prefs
app_textures          cache       databases
generic_x86:/data/data/com.auribises.gw2018a # cd databases
generic_x86:/data/data/com.auribises.gw2018a/databases # ls
Users.db Users.db-journal
generic_x86:/data/data/com.auribises.gw2018a/databases # sqlite3 Users.db
SQLite version 3.19.4 2017-08-18 19:28:12
Enter ".help" for usage hints.
sqlite> .tables
User              android_metadata
sqlite> select * from User;
1|John|9999977777|johnexample.com
2|Jennie|9999944444|jennie@example.com
3|Jim|9898989898|jim@abc
4|Sia|8989898989|sia@example.com
5|Jack|7878787878|jack@example.com
sqlite>
 */

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.editTextName)
    EditText eTxtName;

    @BindView(R.id.editTextPhone)
    EditText eTxtPhone;

    @BindView(R.id.editTextEmail)
    EditText eTxtEmail;

    @BindView(R.id.buttonRegister)
    Button btnRegister;

    User user;

    ContentResolver resolver;

    boolean updateMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);

        Intent rcv = getIntent();
        updateMode = rcv.hasExtra("keyUser");

        btnRegister.setOnClickListener(this);

        user = new User();
        resolver = getContentResolver();

        /*Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        eTxtName.startAnimation(animation1);
        btnRegister.startAnimation(animation2);*/

        if(updateMode){
            user = (User)rcv.getSerializableExtra("keyUser");
            eTxtName.setText(user.name);
            eTxtPhone.setText(user.phone);
            eTxtEmail.setText(user.email);
            btnRegister.setText("Update "+user.name);
            getSupportActionBar().setTitle("Update User");
        }
    }

    void insertUserInDB(){

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,user.name);
        values.put(Util.COL_PHONE,user.phone);
        values.put(Util.COL_EMAIL,user.email);

        if(!updateMode) {
            Uri uri = resolver.insert(Util.USER_URI, values);
            Toast.makeText(this, user.name + " Registered !! " + uri.getLastPathSegment(), Toast.LENGTH_LONG).show();
            clearFields();
        }else{
            String where = Util.COL_ID+" = "+user.id;
            int i = resolver.update(Util.USER_URI,values,where,null);
            if(i>0){
                Toast.makeText(this,user.name+" Updated..."+i,Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this,user.name+" Not Updated..."+i,Toast.LENGTH_LONG).show();
            }
        }
    }

    void clearFields(){
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");

        //spinner.setSelection(0)
    }

    boolean validateFields(){
        boolean flag = true;

        if(user.name.isEmpty())
            flag = false;

        if(user.phone.isEmpty()) {
            flag = false;
        }else{
            if(user.phone.length() != 10){
                flag = false;
            }
        }

        if(user.email.isEmpty()) {
            flag = false;
        }else{
            if( !(user.email.contains(".") && user.email.contains("@")) ){
                flag = false;
            }
        }

        return flag;
    }

    @Override
    public void onClick(View v) {

        user.name = eTxtName.getText().toString().trim();
        user.phone = eTxtPhone.getText().toString().trim();
        user.email = eTxtEmail.getText().toString().trim();

        if(validateFields()) {
            insertUserInDB();
        }else{
            Toast.makeText(this,"Please Enter Correct Details First !!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(!updateMode) {
            menu.add(1, 101, 1, "All Users");
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == 101){
            Intent intent = new Intent(RegisterUserActivity.this,AllUsersActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
