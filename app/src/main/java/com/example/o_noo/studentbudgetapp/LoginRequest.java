package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest
{
    private static final String loginUrl = "http://kunet.kingston.ac.uk/~k1517267/loginFYP.php";

    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener)
    {
        super(Method.POST, loginUrl, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    public Map<String, String> getParams()
    {
        return params;
    }

}
