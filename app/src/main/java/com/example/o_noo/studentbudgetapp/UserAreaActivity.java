package com.example.o_noo.studentbudgetapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final Bundle intentFromLogin = getIntent().getExtras();
        final String forename = intentFromLogin.getString("forename");
        final String surname = intentFromLogin.getString("surname");
        final Integer userId = intentFromLogin.getInt("userId");

        TextView tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage);
        final Button budgetBtn = (Button) findViewById(R.id.budgetBtn);
        final Button assignmentBtn = (Button) findViewById(R.id.listBtn);

        budgetBtn.setOnClickListener(new View.OnClickListener() {
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
                                double currentBalance = JSONResponse.getDouble("currentBalance");
                                int bankId = JSONResponse.getInt("bankId");
                                int incomes = JSONResponse.getInt("income");
                                int expenditures = JSONResponse.getInt("expenditure");
                                Intent intent = new Intent(UserAreaActivity.this, BudgetActivity.class);
                                intent.putExtra("currentBalance", currentBalance);
                                intent.putExtra("incomes", incomes);
                                intent.putExtra("expenditures", expenditures);
                                intent.putExtra("bankId", bankId);
                                UserAreaActivity.this.startActivity(intent);

                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserAreaActivity.this);
                                builder.setMessage(message)
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserAreaActivity.this);
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

                BudgetRequest budgetRequest = new BudgetRequest(userId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserAreaActivity.this);
                queue.add(budgetRequest);

            }
        });





        assignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent assignmentIntent = new Intent(UserAreaActivity.this, AssignmentActivity.class);
                assignmentIntent.putExtra("userId", userId);
                UserAreaActivity.this.startActivity(assignmentIntent);
            }
        });


        String message = "Welcome " + forename + " " + surname + ", please select the following options below";

        tvWelcomeMessage.setText(message);


    }

}
