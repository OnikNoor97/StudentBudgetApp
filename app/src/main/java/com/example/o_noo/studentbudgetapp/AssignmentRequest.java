package com.example.o_noo.studentbudgetapp;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AssignmentRequest extends StringRequest {
    private static final String courseworkUrl = "http://kunet.kingston.ac.uk/~k1517267/assignmentFYP.php";
    private Map<String, String> params;

    public AssignmentRequest(String userId, Response.Listener<String> listener) {
        super(Method.POST, courseworkUrl, listener, null);
        params = new HashMap<>();
        params.put("userId", userId + "");
    }

    public Map<String, String> getParams() {
        return params;
    }
}