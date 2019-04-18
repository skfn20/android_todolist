package com.example.shark.pa1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity {
    private TextView Task;
    private TextView Doing;
    private TextView Done;
    private Intent intent1;
    private Intent intent2;
    private Intent intent3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        Task= (TextView)findViewById(R.id.TASK);
        Doing = (TextView)findViewById(R.id.DOING);
        Done = (TextView)findViewById(R.id.DONE);

        Task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(TodoActivity.this, Task.class);
                startActivity(intent1);
            }
        });

        Doing.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                intent2 = new Intent(TodoActivity.this, Doing.class);
                startActivity(intent2);
            }
        });
        Done.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                intent3 = new Intent(TodoActivity.this, Done.class);
                startActivity(intent3);
            }
        });



    }
}

