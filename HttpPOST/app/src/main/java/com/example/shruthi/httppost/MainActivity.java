package com.example.shruthi.httppost;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendPostRequest(View view) {
        String test = " Shruti says hi";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myReq = new StringRequest(Request.Method.POST,
                "http://192.81.216.37:5000/upload",
                createMyReqSuccessListener(),
                createMyReqErrorListener()) {

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("param1", "3");
                params.put("param2", "4");
                params.put("param3", "5");


                return params;
            };
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(myReq);
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                final TextView mTextView = (TextView) findViewById(R.id.showOutput);
//                String test = "acb";
                mTextView.setText(error.getMessage());
            }
        };
    }

    private Response.Listener<String> createMyReqSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                final TextView mTextView = (TextView) findViewById(R.id.showOutput);
//                String test = "acb";
                mTextView.setText(response);
//                String test = "Done";
            }
        };
    }

    public void sendGetRequest(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.81.216.37:5000/upload";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        String test = "Done";
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}
