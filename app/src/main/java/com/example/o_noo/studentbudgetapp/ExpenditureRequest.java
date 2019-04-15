package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ExpenditureRequest extends StringRequest {
    private static final String expenditureUrl = "http://kunet.kingston.ac.uk/~k1517267/expenditureFYP.php";
    private Map<String, String> params;

    public ExpenditureRequest(Integer bankId, String expenditure, String description, Response.Listener<String> listener) {
        super(Method.POST, expenditureUrl, listener, null);
        params = new HashMap<>();
        params.put("bankId", bankId +"");
        params.put("expenditure", expenditure);
        params.put("description", description);
    }
    public Map<String, String> getParams()
    {
        return params;
    }
}
