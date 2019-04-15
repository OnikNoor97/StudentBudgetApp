package com.example.o_noo.studentbudgetapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ExamRequest extends StringRequest
{
    private static final String examUrl = "http://kunet.kingston.ac.uk/~k1517267/examFYP.php";
    private Map<String, String> params;

    public ExamRequest(String name, String date, String weight, String module, Response.Listener<String> listener)
    {
        super(Method.POST, examUrl, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("date", date );
        params.put("weight", weight);
        params.put("module", module);
    }

    public Map<String, String> getParams()
    {
        return params;
    }
}
