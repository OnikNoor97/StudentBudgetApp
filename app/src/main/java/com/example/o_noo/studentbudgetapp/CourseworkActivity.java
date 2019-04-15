package com.example.o_noo.studentbudgetapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CourseworkActivity extends AppCompatActivity {

    Integer counter = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursework);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etDeadline = (EditText) findViewById(R.id.etDeadline);
        final EditText etWeight = (EditText) findViewById(R.id.etWeight);
        Button courseworkBtn = (Button) findViewById(R.id.courseworkBtn);
        Button modulesBtn = (Button) findViewById(R.id.moduleBtn);
        final TextView tvModules = (TextView) findViewById(R.id.tvModules);



        Bundle intentFromAssignment = getIntent().getExtras();
        final Integer moduleSize = intentFromAssignment.getInt("moduleSize");
        final String[] modules = intentFromAssignment.getStringArray("moduleList");



        modulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                if (counter >= moduleSize)
                {
                    counter = 0;
                    tvModules.setText(modules[counter]);

                }
                else
                {
                    tvModules.setText(modules[counter]);
                }
            }
        });



        courseworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectedModule = tvModules.getText().toString();
                String name = etName.getText().toString();
                String deadline = etDeadline.getText().toString();
                String weight = etWeight.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject JSONResponse = new JSONObject(response);
                            boolean success = JSONResponse.getBoolean("success");
                            String message = JSONResponse.getString("message");



                            if (success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CourseworkActivity.this);
                                builder.setMessage(message)
                                        .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CourseworkActivity.this);
                                builder.setMessage(message)
                                        .setPositiveButton("Retry", null)
                                        .create()
                                        .show();
                            }


                        }
                        catch(JSONException e)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CourseworkActivity.this);
                            builder.setMessage(e.toString())
                                    .setNegativeButton("Reset", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                    }
                };

                if (counter > -1) {
                    CourseworkRequest request = new CourseworkRequest(name, deadline, weight, selectedModule, listener);
                    RequestQueue queue = Volley.newRequestQueue(CourseworkActivity.this);
                    queue.add(request);
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CourseworkActivity.this);
                    builder.setMessage("Please select a module")
                            .setNegativeButton("Retry" , null)
                            .create()
                            .show();
                }

            }
        });

    }
}
