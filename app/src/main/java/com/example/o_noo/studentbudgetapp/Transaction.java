package com.example.o_noo.studentbudgetapp;


public class Transaction
{
    public Integer id;
    private double amount;
    private String description;
    private String date;

    public Transaction(Integer id, double amount, String description, String date)
    {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
