package com.example.o_noo.studentbudgetapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        Button moduleBtn = (Button) findViewById(R.id.moduleBtn);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etWeight = (EditText) findViewById(R.id.etWeight);

        moduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String weight = etWeight.getText().toString();
                Bundle intentFromAssignment = getIntent().getExtras();
                Integer userId = intentFromAssignment.getInt("userId");


                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONObject JSONResponse = new JSONObject(response);
                            boolean success = JSONResponse.getBoolean("success");
                            String message = JSONResponse.getString("message");


                            if (success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ModuleActivity.this);
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(ModuleActivity.this);
                                builder.setMessage(message)
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        }
                        catch (JSONException e)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ModuleActivity.this);
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

                Integer moduleWeight = Integer.valueOf(weight);
                ModuleRequest moduleRequest = new ModuleRequest(userId, name, moduleWeight, listener);
                RequestQueue queue = Volley.newRequestQueue(ModuleActivity.this);
                queue.add(moduleRequest);


            }
        });
    }
}
