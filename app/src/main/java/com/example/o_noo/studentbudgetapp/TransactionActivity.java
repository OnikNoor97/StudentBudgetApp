package com.example.o_noo.studentbudgetapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity
{
    List<Transaction> incomeList = new ArrayList();
    List<Transaction> expenditureList = new ArrayList();
    int counter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Bundle intentFromBudget = getIntent().getExtras();
        final Integer bankId = intentFromBudget.getInt("bankId");
        TextView tvStatement = (TextView) findViewById(R.id.tvStatement);
        final Button typeBtn = (Button) findViewById(R.id.typeBtn);
        final Button toggleBtn = (Button) findViewById(R.id.toggleBtn);
        final TextView tvType = (TextView) findViewById(R.id.tvType);
        final TextView tvAmount = (TextView) findViewById(R.id.tvAmount);
        final TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        final TextView tvDate = (TextView) findViewById(R.id.tvDate);


        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try
                {
                    JSONObject JSONResponse = new JSONObject(response);
                    boolean success = JSONResponse.getBoolean("success");
                    String message = JSONResponse.getString("message");
                    Integer incomesSize = JSONResponse.getInt("incomesSize");
                    Integer expendituresSize = JSONResponse.getInt("expendituresSize");
                    JSONArray incomes = JSONResponse.getJSONArray("incomes");
                    JSONArray expenditures = JSONResponse.getJSONArray("expenditures");

                    for (int i = 0; i < incomesSize; i++)
                    {
                        JSONObject income = incomes.getJSONObject(1);
                        Integer incomeId = income.getInt("0");
                        double amount = income.getDouble("1");
                        String description = income.getString("2");
                        String date = income.getString("3");
                        incomeList.add(new Transaction(incomeId, amount, description, date));
                    }

                    for (int i = 0; i < expendituresSize; i++)
                    {
                        JSONObject expenditure = expenditures.getJSONObject(i);
                        Integer incomeId = expenditure.optInt("expenditureId");
                        double amount = expenditure.optDouble("amount");
                        String description = expenditure.optString("description");
                        String date = expenditure.optString("date");
                        expenditureList.add(new Transaction(incomeId, amount, description, date));
                    }

                }
                catch (JSONException e)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TransactionActivity.this);
                    builder.setMessage(e.toString())
                            .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .create()
                            .show();
                }



                tvType.setText("Income");
                typeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (tvType.getText() == "Income")
                        {
                            tvType.setText("Expenditure");
                        }
                        else
                        {
                            tvType.setText("Income");
                        }
                    }
                });


                toggleBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter++;

                        if (tvType.getText().toString().equals("Income")) {
                            if (counter < 0 || counter > incomeList.size())
                            {
                                counter = 0;
                            }
                            else {
                                Transaction selectedTransaction = incomeList.get(counter);
                                tvAmount.setText(selectedTransaction.getId().toString());
                                tvDescription.setText(selectedTransaction.getDescription());
                                tvDate.setText(selectedTransaction.getDate());
                            }
                        }
                        if (tvType.getText().toString().equals("Expenditure")) {

                            if (counter < 0 || counter > incomeList.size())
                            {
                                counter = 0;
                            }
                            else {
                                Transaction selectedTransaction = expenditureList.get(counter);
                                tvAmount.setText(selectedTransaction.getId().toString());
                                tvDescription.setText(selectedTransaction.getDescription());
                                tvDate.setText(selectedTransaction.getDate());
                            }
                        }

                    }
                });

            }
        };

        TransactionRequest request = new TransactionRequest(bankId, listener);
        RequestQueue queue = Volley.newRequestQueue(TransactionActivity.this);
        queue.add(request);

    }

}
