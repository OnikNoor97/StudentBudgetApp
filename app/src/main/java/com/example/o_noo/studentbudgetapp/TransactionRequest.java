package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TransactionRequest extends StringRequest
{

    private static final String transactionUrl = "http://kunet.kingston.ac.uk/~k1517267/transactionsFYP.php";
    private Map<String, String> params;

    public TransactionRequest(Integer bankId, Response.Listener<String> listener)
    {
        super(Method.POST, transactionUrl, listener, null);
        params = new HashMap<>();
        params.put("bankId", bankId + "");
    }

    public Map<String, String> getParams()
    {
        return params;
    }
}
