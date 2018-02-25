package com.example.vaishnavgubba.a2ndpollsrus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String url = "https://pollapp-v1.herokuapp.com/api/questionData";
    private RequestQueue rQueue;
    private ArrayList<questionData> questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        questionId = new ArrayList<>();

        rQueue = Volley.newRequestQueue(this);
        rQueue.start();

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        //mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        StringRequest stringReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray s = new JSONArray(response);
                            for(int i = 0; i < s.length(); i++){
                                JSONObject o = (JSONObject)s.get(i);
                                questionId.add(new questionData(o.get("question").toString(), o.get("_id").toString()));
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        rQueue.add(stringReq);

        mAdapter = new MyAdapter(questionId);
        mRecyclerView.setAdapter(mAdapter);
    }
}
