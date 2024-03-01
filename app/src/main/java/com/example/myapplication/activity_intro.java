package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class activity_intro extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btn=(Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openpagelogin();

            };
        });






    }
    public void openpagelogin(){
        finish();
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}