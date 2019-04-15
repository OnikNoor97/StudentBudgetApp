package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ModuleRequest extends StringRequest
{
    private static final String moduleUrl = "http://kunet.kingston.ac.uk/~k1517267/moduleFYP.php";
    private Map<String, String> params;

    public ModuleRequest(Integer userId, String name, int weight, Response.Listener<String> listener)
    {
        super(Method.POST, moduleUrl, listener, null);
        params = new HashMap<>();
        params.put("userId", userId +"");
        params.put("name", name);
        params.put("moduleWeight", weight + "");
    }

    public Map<String, String> getParams()
    {
        return params;
    }
}
