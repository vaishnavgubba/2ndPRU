package com.example.vaishnavgubba.a2ndpollsrus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button sign = findViewById(R.id.signin);
        Button reg = findViewById(R.id.register);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(view);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(view);
            }
        });

    }


    public void registerUser(View view) {
        Intent startNewAct = new Intent(this, Main4Activity.class);
        startActivity(startNewAct);
    }

    public void signIn(View view) {
        Intent startNewAct = new Intent(this, Main2Activity.class);
        startActivity(startNewAct);
    }
}
