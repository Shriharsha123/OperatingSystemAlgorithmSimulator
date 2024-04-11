package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputLayout;

public class proSchedActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proc_sched);
        Button createProcessBt =  findViewById(R.id.create_process);
        Button createResourceBt =  findViewById(R.id.create_res);
        createProcessBt.setOnClickListener(new View.OnClickListener() {
            int procNum=0;
            @Override
            public void onClick(View view)
            {
                procNum=procNum+1;
                addProcessInput(procNum);
            }
        });

        createResourceBt.setOnClickListener(new View.OnClickListener() {
            int resNum=0;
            @Override
            public void onClick(View view)
            {
                resNum=resNum+1;
                addResourceInput(resNum);
            }
        });
    }

    private void addProcessInput(int procNum)
    {
        LinearLayout layout= findViewById(R.id.procCreateCol);

        TextInputLayout newInput= new TextInputLayout(this);

        newInput.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        EditText editText= new EditText(this);
        editText.setLayoutParams(editTextParams);
        editText.setHint("Process " + procNum + " name");

        layout.addView(newInput);
        newInput.addView(editText, editTextParams);
    }
    private void addResourceInput(int resNum)
    {
        LinearLayout layout= findViewById(R.id.resCreateCol);

        TextInputLayout newInput= new TextInputLayout(this);

        newInput.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        EditText editText= new EditText(this);
        editText.setLayoutParams(editTextParams);
        editText.setHint("Resource " + resNum + " name");

        layout.addView(newInput);
        newInput.addView(editText, editTextParams);
    }
}
