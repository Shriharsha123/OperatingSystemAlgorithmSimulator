package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class paging extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paging);
        Button submitBtn=findViewById(R.id.submitPages);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFilled())
                    getPages();
                else
                    Toast.makeText(getApplicationContext(),"Fields are incorrectly filled",Toast.LENGTH_LONG).show();
            }
        });

    }
    public static int pageFaults(int[] pages, int n, int capacity)
    {
                HashSet<Integer> s = new HashSet<>(capacity);
                Queue<Integer> indexes = new LinkedList<>() ;
                int page_faults = 0;
                for (int i=0; i<n; i++)
                {
                    if (s.size() < capacity)
                    {
                        if (!s.contains(pages[i]))
                        {
                            s.add(pages[i]);
                            page_faults++;
                            indexes.add(pages[i]);
                        }
                    }
                    else
                    {
                        if (!s.contains(pages[i]))
                        {
                            int val = indexes.peek();
                            indexes.poll();
                            s.remove(val);
                            s.add(pages[i]);
                            indexes.add(pages[i]);
                            page_faults++;
                        }
                    }
                }

                return page_faults;

        }
        public void getPages()
        {
            TextInputEditText requests=findViewById(R.id.editReq);
            TextInputEditText capacity=findViewById(R.id.editCap);
            String reqString=requests.getText().toString();
            String[] pags =reqString.split(",");
            int n=pags.length;
            int[] pages=new int[n];
            try {
                for (int i = 0; i < n; i++) {
                    pages[i] = Integer.parseInt(pags[i]);
                }
            }
            catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(),"Fields are incorrectly filled",Toast.LENGTH_LONG).show();
            }
            int cap =Integer.parseInt(capacity.getText().toString());
            System.out.println(pageFaults(pages, pages.length, cap));
        }
        public boolean isFilled()
        {
            TextInputEditText requests=findViewById(R.id.editReq);
            TextInputEditText capacity=findViewById(R.id.editCap);
            String input=requests.getText().toString();
            String input2=capacity.getText().toString();
            return !input.isEmpty() && !input2.isEmpty();
        }
}
