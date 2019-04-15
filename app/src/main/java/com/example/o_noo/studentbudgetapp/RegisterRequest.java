package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest
{
    private static final String registerUrl = "http://kunet.kingston.ac.uk/~k1517267/registerFYP.php";
    
    private Map<String, String> params;

    public RegisterRequest(String forename, String surname, String username, String password, Response.Listener<String> listener)
    {
        super(Method.POST, registerUrl, listener, null);
        params = new HashMap<>();
        params.put("forename", forename);
        params.put("surname", surname);
        params.put("username", username);
        params.put("password", password);
    }

    public Map<String, String> getParams()
    {
        return params;
    }
}