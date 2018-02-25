package com.example.vaishnavgubba.a2ndpollsrus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private RequestQueue rQueue;
    private String url = "https://pollapp-v1.herokuapp.com/api/questionData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rQueue = Volley.newRequestQueue(this);
        rQueue.start();

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject questionData = new JSONObject();
                try{
                    EditText q = findViewById(R.id.INPUTQ);
                    EditText o1 = findViewById(R.id.OPTION1);
                    EditText o2 = findViewById(R.id.OPTION2);
                    EditText o3 = findViewById(R.id.OPTION3);
                    EditText o4 = findViewById(R.id.OPTION4);
                    questionData.put("question", q.getText().toString());
                    JSONObject op1 = new JSONObject();
                    op1.put("body", o1.getText().toString());
                    op1.put("count", 0);
                    questionData.put("option1", op1);
                    JSONObject op2 = new JSONObject();
                    op2.put("body", o2.getText().toString());
                    op2.put("count", 0);
                    questionData.put("option2", op2);
                    JSONObject op3 = new JSONObject();
                    op3.put("body", o3.getText().toString());
                    if(o3.getText().toString().equals(""))
                        op3.put("count", -1);
                    else
                        op3.put("count", 0);
                    questionData.put("option3", op3);

                    JSONObject op4 = new JSONObject();
                    op4.put("body", o4.getText().toString());
                    if(o4.getText().toString().equals(""))
                        op4.put("count", -1);
                    else
                        op4.put("count", 0);
                    questionData.put("option4", op4);
                }catch(JSONException e){
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, questionData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                rQueue.add(jsonObjReq);

            }
        });




    }
}
