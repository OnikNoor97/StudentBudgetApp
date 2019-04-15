package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class IncomeRequest extends StringRequest {
    private static final String incomeURL = "http://kunet.kingston.ac.uk/~k1517267/incomeFYP.php";
    private Map<String, String> params;

    public IncomeRequest(Integer bankId, String income, String description, Response.Listener<String> listener) {
        super(Method.POST, incomeURL, listener, null);
        params = new HashMap<>();
        params.put("bankId", bankId + "");
        params.put("income", income);
        params.put("description", description);
    }

    public Map<String, String> getParams()
    {
        return params;
    }
}
