package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BudgetRequest extends StringRequest {
    private static final String budgetURL = "http://kunet.kingston.ac.uk/~k1517267/budgetFYP.php";
    private Map<String, String> params;

    public BudgetRequest(Integer userId, Response.Listener<String> listener) {
        super(Method.POST, budgetURL, listener, null);
        params = new HashMap<>();
        params.put("userId", userId +"");
    }

    public Map<String, String> getParams()
    {
        return params;
    }
}