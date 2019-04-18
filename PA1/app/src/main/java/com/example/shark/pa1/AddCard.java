package com.example.shark.pa1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCard extends AppCompatActivity {
    private EditText job;
    private EditText deadline;
    private EditText desc;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        job = (EditText)findViewById(R.id.job_edit);
        deadline = (EditText)findViewById(R.id.deadline_edit);
        desc = (EditText)findViewById(R.id.des_edit);
        add = (Button)findViewById(R.id.add_button);
        Intent intent = getIntent();
        int request = intent.getIntExtra("request", -1);
        final int position = intent.getIntExtra("position", 0);
        if(request == 2){
            Log.e("Log", "AddCard -> working on the request == 2");
            String job_txt = intent.getStringExtra("job");
            String deadline_txt = intent.getStringExtra("deadline");
            String detail_txt = intent.getStringExtra("detail");

            job.setText(job_txt);
            deadline.setText(deadline_txt);
            desc.setText(detail_txt);
        }

        add.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent2 = new Intent();
                    intent2.putExtra("job", job.getText().toString());
                    intent2.putExtra("deadline", deadline.getText().toString());
                    intent2.putExtra("description", desc.getText().toString());
                    intent2.putExtra("position", position);
                    Log.e("일", job.getText().toString());
                    Log.e("deadline", deadline.getText().toString());
                    Log.e("desc", desc.getText().toString());
                    setResult(Activity.RESULT_OK, intent2);
                    finish();
                }
        });
    }
}
