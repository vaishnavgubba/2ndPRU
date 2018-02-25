package com.example.vaishnavgubba.a2ndpollsrus;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main4Activity extends AppCompatActivity {
    private RequestQueue rQueue;
    private boolean unique;
    private String url = "https://pollapp-v1.herokuapp.com/api/userData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        rQueue = Volley.newRequestQueue(this);
        rQueue.start();

        Button reg = findViewById(R.id.registerUser);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText)findViewById(R.id.username)).getText().toString();
                String password = ((EditText)findViewById(R.id.regpassword)).getText().toString();
                String reenterpw = ((EditText)findViewById(R.id.regpassword2)).getText().toString();
                uniqueUsername(username);
                if(!unique) {
                    Log.d("MYDEBUGGER3", unique+"");
                    ((TextView) findViewById(R.id.regError)).setText("");
                    ((TextView) findViewById(R.id.regError)).setText(R.string.userNotUnique);
                } else{
                    Log.d("MYDEBUGGER3", unique+"");
                    ((TextView) findViewById(R.id.regError)).setText("");
                    ((TextView) findViewById(R.id.regError)).setText(R.string.userUnique);
                }
                /*else if(!password.equals(reenterpw)) {
                    ((TextView) findViewById(R.id.regError)).setText("");
                    ((TextView) findViewById(R.id.regError)).setText(R.string.passwordMatch);
                }
                else{
                    //post new user
                    JSONObject userObj = new JSONObject();
                    try{
                        userObj.put("username", username);
                        userObj.put("password", password);
                    } catch(JSONException e){
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                            url, userObj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    rQueue.add(jsonObjReq);

                }*/
            }
        });

    }

    public void uniqueUsername(final String uName){
        StringRequest stringReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            unique = true;
                            JSONArray s = new JSONArray(response);
                            int i = 0;
                            while(i < s.length() && unique){
                                JSONObject o = (JSONObject)s.get(i);
                                if(o.get("username").toString().equals(uName))
                                    unique = false;
                                //Log.d("MYDEBUGGER", o.get("username").toString());
                                i++;
                            }
                            Log.d("MYDEBUGGER1", unique+"");
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
        //Log.d("MYDEBUGGER", "Done printing");

        Log.d("MYDEBUGGER2", unique+"");

        //return unique;
    }

}
