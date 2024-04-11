package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button gotoProc =  findViewById(R.id.procSched);
        gotoProc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i= new Intent(MainActivity.this,proSchedActivity.class);
                startActivity(i);
            }
        });
        Button gotoDeadDetect =  findViewById(R.id.deadDetect);
        gotoDeadDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent j= new Intent(MainActivity.this,dead_detect.class);
                startActivity(j);
            }
        });


    }
}