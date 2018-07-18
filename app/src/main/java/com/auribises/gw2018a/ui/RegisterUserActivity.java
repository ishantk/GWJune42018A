package com.auribises.gw2018a.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.auribises.gw2018a.R;
import com.auribises.gw2018a.model.User;


import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);

        btnRegister.setOnClickListener(this);

        user = new User();
    }

    @Override
    public void onClick(View v) {

        user.name = eTxtName.getText().toString().trim();
        user.phone = eTxtPhone.getText().toString().trim();
        user.email = eTxtEmail.getText().toString().trim();


    }
}
