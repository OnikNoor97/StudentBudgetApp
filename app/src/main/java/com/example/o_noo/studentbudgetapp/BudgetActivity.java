package com.example.o_noo.studentbudgetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BudgetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        final Button incomeBtn = (Button) findViewById(R.id.incomeBtn);
        final Button expenditureBtn = (Button) findViewById(R.id.expenditureBtn);
        final Button transactionBtn = (Button) findViewById(R.id.transactionBtn);
        final TextView tvCurrentBalance = (TextView) findViewById(R.id.tvCurrentBalance);
        final TextView tvIncome = (TextView) findViewById(R.id.tvIncome);
        final TextView tvExpenditure = (TextView) findViewById(R.id.tvExpenditure);

        final Bundle fromUserArea = getIntent().getExtras();
        final double currentBalance = fromUserArea.getDouble("currentBalance");
        final int bankId = fromUserArea.getInt("bankId");
        final int incomes = fromUserArea.getInt("incomes");
        final int expenditures = fromUserArea.getInt("expenditures");

        Extra extra = new Extra();
        String string1 = extra.lol(incomes);
        String string2 = extra.lol(expenditures);


        incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent incomeIntent = new Intent(BudgetActivity.this, IncomeActivity.class);
                incomeIntent.putExtra("currentBalance", currentBalance);
                incomeIntent.putExtra("bankId", bankId);
                BudgetActivity.this.startActivity(incomeIntent);
            }
        });

        expenditureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent expenditureIntent = new Intent(BudgetActivity.this, ExpenditureActivity.class);
                expenditureIntent.putExtra("currentBalance", currentBalance);
                expenditureIntent.putExtra("bankId", bankId);
                BudgetActivity.this.startActivity(expenditureIntent);
            }
        });

        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transactionIntent = new Intent(BudgetActivity.this, TransactionActivity.class);
                transactionIntent.putExtra("bankId", bankId);
                BudgetActivity.this.startActivity(transactionIntent);
            }
        });

        DecimalFormat df2 = new DecimalFormat();
        df2.setMinimumFractionDigits(2);
        String currentBalance2dp = df2.format(currentBalance);
        String statement = "Your current balance is: " + currentBalance2dp;

        String incomeStmt = "You have made " + incomes + " income " + string1;
        String expenditureStmt = "You have made " + expenditures + " expenditure " + string2;

        tvCurrentBalance.setText(statement);
        tvIncome.setText(incomeStmt);
        tvExpenditure.setText(expenditureStmt);
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
