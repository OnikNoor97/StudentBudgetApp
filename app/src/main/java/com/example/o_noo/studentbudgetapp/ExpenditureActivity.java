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

import java.text.DecimalFormat;

public class ExpenditureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);

        final EditText etExpenditure = (EditText) findViewById(R.id.etExpenditure);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);
        final Button expenditureBtn = (Button) findViewById(R.id.expenditureBtn);
        final TextView tvCurrentBalance = (TextView) findViewById(R.id.tvCurrentBalance);

        final Bundle intentFromBudget = getIntent().getExtras();
        double currentBalance = intentFromBudget.getDouble("currentBalance");
        final Integer bankId = intentFromBudget.getInt("bankId");

        expenditureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String expenditure = etExpenditure.getText().toString();
                final String description = etDescription.getText().toString();

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
                                AlertDialog.Builder builder = new AlertDialog.Builder(ExpenditureActivity.this);
                                builder.setMessage(message)
                                        .setNegativeButton("Proceed", new DialogInterface.OnClickListener() {
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(ExpenditureActivity.this);
                                builder.setMessage(message)
                                        .setNegativeButton(message, null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ExpenditureActivity.this);
                            builder.setMessage(e.toString())
                                    .setNegativeButton("Reset", null)
                                    .create()
                                    .show();
                        }
                    }
                };

                ExpenditureRequest registerRequest = new ExpenditureRequest(bankId, expenditure, description, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ExpenditureActivity.this);
                queue.add(registerRequest);
            }
        });

        DecimalFormat df2 = new DecimalFormat();
        df2.setMinimumFractionDigits(2);
        String currentBalance2dp = df2.format(currentBalance);
        tvCurrentBalance.setText("Your current balance is: £" + currentBalance2dp    + " Please input the information of your expenditure");


    }
}
