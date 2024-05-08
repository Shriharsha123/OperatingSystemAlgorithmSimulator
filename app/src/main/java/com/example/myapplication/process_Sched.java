package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

class Process
{
    String name;
    int at;
    int bt;

    Process next;


    public Process() {
        this.name = null;
        this.at= -1;
        this.bt=-1;
        this.next=null;
    }
    public String getName()
    {
        return this.name;
    }
    public int getat()
    {
        return this.at;
    }
    public int getbt()
    {
        return this.bt;
    }
    public void setName(String str)
    {
        this.name=str;

    }
    public void setAt(int arrt)
    {
        this.at=arrt;

    }
    public void setBt(int burt)
    {
        this.bt=burt;

    }
    public boolean checkFill()
    {
        return this.name != null && this.at >= 0 && this.bt >= 0;
    }


}

public class process_Sched extends AppCompatActivity{
    Process head=null;
    int procNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_sched);


        Button createProcessBt =  findViewById(R.id.addProc);
        Button submitBt= findViewById(R.id.submitProcSched);
        createProcessBt.setOnClickListener(view -> {
            Process tail=getTail();
            if(tail==null)
            {
                procNum = procNum + 1;
                addProcessInput(procNum);
            }
            //int procNum=0;
            else if(tail.checkFill()) {
                procNum = procNum + 1;
                addProcessInput(procNum);
            }
            else
            {
                Toast.makeText(process_Sched.this, "You did not enter values", Toast.LENGTH_SHORT).show();
            }
        });
        submitBt.setOnClickListener(view -> takeSubmission(procNum));
    }
    private void addProcessInput(int procNum)
    {
        LinearLayout parent1= findViewById(R.id.procNameRow);
        LinearLayout parent2= findViewById(R.id.atRow);
        LinearLayout parent3= findViewById(R.id.btRow);


        TextInputLayout procName= new TextInputLayout(this);
        procName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText editText= new EditText(this);
        editText.setLayoutParams(editTextParams);
        editText.setHint("Process " + procNum + " name");

        TextInputLayout at= new TextInputLayout(this);
        at.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        EditText editText2= new EditText(this);
        editText2.setLayoutParams(editTextParams);
        editText2.setHint("Arrival Time");
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER);

        TextInputLayout bt= new TextInputLayout(this);
        bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        EditText editText3= new EditText(this);
        editText3.setLayoutParams(editTextParams);
        editText3.setHint("Burst Time");
        editText3.setInputType(InputType.TYPE_CLASS_NUMBER);

        parent1.addView(procName);
        parent2.addView(at);
        parent3.addView(bt);
        procName.addView(editText, editTextParams);
        at.addView(editText2, editTextParams);
        bt.addView(editText3, editTextParams);

        Process newProc;
       if(head==null)
       {
           head=new Process();
           newProc=head;
       }
       else
       {
           Process temp=getTail();
           newProc=new Process();
           temp.next=newProc;
       }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                String input = editText.getText().toString();
                boolean filled;
                if (input.isEmpty()) {
                    filled = false;
                    newProc.setName(null);
                }
                else
                    filled=true;
                if (filled)
                    newProc.setName(input);
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                String input2 = editText2.getText().toString();
                boolean filled;
                if (input2.isEmpty()) {
                    filled = false;
                    newProc.setAt(-1);
                }
                else
                    filled=true;
                if (filled)
                    newProc.setAt(Integer.parseInt(input2));
            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                String input3 = editText3.getText().toString();
                boolean filled;
                if (input3.isEmpty()) {
                    filled = false;
                    newProc.setBt(-1);
                }
                else
                    filled=true;
                if (filled)
                    newProc.setBt(Integer.parseInt(input3));
            }
        });

    }

    private Process getTail() {
        Process result = null;
        if (head != null) {
            Process temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            result = temp;
        }
        return result;
    }

    private String getProcName(int num)
    {
            Process temp=head;
            int count=0;
            while(count<num)
            {
                temp=temp.next;
                count+=1;
            }
            return temp.getName();
        }

    private int getProcat(int num)
    {
        Process temp=head;
        int count=0;
        while(count<num)
        {
            temp=temp.next;
            count+=1;
        }
        return temp.getat();
    }

    private int getProcbt(int num)
    {
        Process temp=head;
        int count=0;
        while(count<num)
        {
            temp=temp.next;
            count+=1;
        }
        return temp.getbt();
    }

    private void takeSubmission(int n) {
            int[] at=new int[n];
            int[] bt=new int[n];
            String[] name=new String[n];
            int i,j;
            for(i=0;i<n;i++)
            {
                at[i]=getProcat(i);
                bt[i]=getProcbt(i);
                name[i]=getProcName(i);
            }
            System.out.println("\nInitial table:\np\t A.T\t B.T");
            for (i = 0; i < n; i++) {
                System.out.println("P" + name[i] + "\t " + at[i] + "\t " + bt[i]);
            }

            int[] ct = new int[n];
            int[] tat = new int[n];
            int[] wt = new int[n];
            int temp;
            float awt = 0, atat = 0;

            for (i = 0; i < n; i++) {
                for (j = i+1; j < n ; j++) {
                    if (at[j] < at[i]) {
                        String tempName = name[j];
                        name[j] = name[i];
                        name[i] = tempName;
                        temp = at[j];
                        at[j] = at[i];
                        at[i] = temp;
                        temp = bt[j];
                        bt[j] = bt[i];
                        bt[i] = temp;
                    }
                }
            }

            ct[0] = at[0] + bt[0];

            for (i = 1; i < n; i++) {
                temp = 0;
                if (ct[i - 1] < at[i]) {
                    temp = at[i] - ct[i - 1];
                }
                ct[i] = ct[i - 1] + bt[i] + temp;
            }
            System.out.println("Procnum: " + n);

            System.out.println("\np\t A.T\t B.T\t C.T\t TAT\t WT");
            for (i = 0; i < n; i++) {
                tat[i] = ct[i] - at[i];
                wt[i] = tat[i] - bt[i];
                atat += tat[i];
                awt += wt[i];
            }
            atat = atat / n;
            awt = awt / n;

            for (i = 0; i < n; i++) {
                System.out.println("P" + name[i] + "\t " + at[i] + "\t " + bt[i] + "\t " + ct[i] + "\t " + tat[i] + "\t " + wt[i]);
            }
            System.out.println("average turnaround time is " + atat);
            System.out.println("average waiting time is " + awt);
        }
}
