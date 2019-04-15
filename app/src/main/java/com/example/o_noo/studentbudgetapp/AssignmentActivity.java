package com.example.o_noo.studentbudgetapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AssignmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        Button courseworkBtn = (Button) findViewById(R.id.courseworkBtn);
        Button examBtn = (Button) findViewById(R.id.examBtn);
        final Button moduleBtn = (Button) findViewById(R.id.moduleBtn);

        Bundle intentFromUserArea = getIntent().getExtras();
        final Integer userId = intentFromUserArea.getInt("userId");

        courseworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONObject JSONResponse = new JSONObject(response);
                            boolean success = JSONResponse.getBoolean("success");
                            String message = JSONResponse.getString("message");

                            if (success)
                            {
                                JSONArray modules = JSONResponse.getJSONArray("modules");
                                Integer moduleSize = JSONResponse.getInt("moduleSize");
                                String[] moduleList = new String[moduleSize];
                                for (int i = 0; i < moduleSize; i++)
                                {
                                    JSONObject module = modules.getJSONObject(i);
                                    String moduleString = module.getString("name");
                                    moduleList[i] = moduleString;
                                }
                                Intent intentToCoursework = new Intent(AssignmentActivity.this, CourseworkActivity.class);
                                intentToCoursework.putExtra("moduleSize", moduleSize);
                                intentToCoursework.putExtra("moduleList", moduleList);
                                AssignmentActivity.this.startActivity(intentToCoursework);
                            }
                            else if (!success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AssignmentActivity.this);
                                builder.setMessage(message)
                                        .setNegativeButton("Retry" , null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(AssignmentActivity.this);
                            builder.setMessage(e.toString());
                            builder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
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

                AssignmentRequest assignmentRequest = new AssignmentRequest("" + userId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AssignmentActivity.this);
                queue.add(assignmentRequest);


            }
        });

        examBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONObject JSONResponse = new JSONObject(response);
                            boolean success = JSONResponse.getBoolean("success");
                            String message = JSONResponse.getString("message");

                            if (success)
                            {
                                JSONArray modules = JSONResponse.getJSONArray("modules");
                                Integer moduleSize = JSONResponse.getInt("moduleSize");
                                String[] moduleList = new String[moduleSize];
                                for (int i = 0; i < moduleSize; i++)
                                {
                                    JSONObject module = modules.getJSONObject(i);
                                    String moduleString = module.getString("name");
                                    moduleList[i] = moduleString;
                                }
                                Intent intentToCoursework = new Intent(AssignmentActivity.this, ExamActivity.class);
                                intentToCoursework.putExtra("moduleSize", moduleSize);
                                intentToCoursework.putExtra("moduleList", moduleList);
                                AssignmentActivity.this.startActivity(intentToCoursework);
                            }
                            else if (!success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AssignmentActivity.this);
                                builder.setMessage(message)
                                        .setNegativeButton("Retry" , null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(AssignmentActivity.this);
                            builder.setMessage(e.toString());
                            builder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
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

                AssignmentRequest assignmentRequest = new AssignmentRequest("" + userId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AssignmentActivity.this);
                queue.add(assignmentRequest);

            }
        });

        moduleBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intentToModule = new Intent(AssignmentActivity.this, ModuleActivity.class);
                intentToModule.putExtra("userId", userId);
                AssignmentActivity.this.startActivity(intentToModule);

            }
        });
    }
}