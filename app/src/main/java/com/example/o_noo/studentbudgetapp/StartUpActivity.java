package com.example.o_noo.studentbudgetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartUpActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        final Button loginbtn = (Button) findViewById(R.id.loginBtn);
        final Button registerbtn = (Button) findViewById(R.id.registerBtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(StartUpActivity.this, LoginActivity.class);
                StartUpActivity.this.startActivity(loginIntent);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(StartUpActivity.this, RegisterActivity.class);
                StartUpActivity.this.startActivity(registerIntent);
            }
        });

    }
}