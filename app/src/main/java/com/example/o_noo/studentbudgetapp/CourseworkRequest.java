package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CourseworkRequest extends StringRequest
{
    private static final String courseworkUrl = "http://kunet.kingston.ac.uk/~k1517267/courseworkFYP.php";
    private Map<String, String> params;

    public CourseworkRequest(String name, String date, String weight, String moduleName, Response.Listener<String> listener)
    {
        super(Method.POST, courseworkUrl, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("deadline", date);
        params.put("weight", weight);
        params.put("module", moduleName);
    }

    public Map<String, String> getParams()
    {
        return params;
    }
}
