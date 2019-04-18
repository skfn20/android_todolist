package com.example.shark.pa1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Task extends AppCompatActivity implements ListViewAdapter.ListBtnClickListener{
    private Button task_button;
    public static final int REQUEST = 1;
    public static final int REQUEST_EDIT = 2;
    public static final int REQUEST_MOVE = 3;
    private ListView task_view;
    private ListViewAdapter adapter;
    private TextView task;
    private MyApplication myApp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        task = (TextView)findViewById(R.id.task);
        myApp = (MyApplication)getApplication();
        task_view = (ListView)findViewById(R.id.list_task);

        adapter = new ListViewAdapter(this, R.layout.listview_item, myApp.task_list, this);
        task_view.setAdapter(adapter);
        task_button = (Button)findViewById(R.id.add_task);

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", 0);
        int request = intent.getIntExtra("request", -1);
        if(request == 3) {
            Log.e("Doing", "request3 working");
            String job = intent.getStringExtra("job");
            String deadline = intent.getStringExtra("deadline");
            String desc = intent.getStringExtra("description");
            ListViewItem item = new ListViewItem();
            item.setJob(job);
            item.setDeadline(deadline);
            item.setDetail(desc);
            myApp.task_list.add(item);
            adapter.notifyDataSetChanged();
            Intent intent2 = new Intent();
            intent2.putExtra("position", position);
            setResult(Activity.RESULT_OK, intent2);
            finish();
        }

        task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Task.this, AddCard.class);
                intent.putExtra("request", 1);
                startActivityForResult(intent, REQUEST);
            }
        });
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(getApplicationContext(), v);
                getMenuInflater().inflate(R.menu.menu_task, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getApplicationContext(), ""+item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        String s = item.getTitle().toString();
                        switch(s){
                            case "Doing":
                                Intent intent2 = new Intent(Task.this, Doing.class);
                                startActivity(intent2);
                                break;
                            case "Done":
                                Intent intent3 = new Intent(Task.this, Done.class);
                                startActivity(intent3);
                                break;
                        }

                        return false;
                    }
                });
                p.show();
            }
        });
        task_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("OnItemClick", "성공");
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST){
            if(resultCode == Activity.RESULT_OK){
                Log.e("Log", "성공");
                String job = data.getStringExtra("job");
                String deadline = data.getStringExtra("deadline");
                String desc = data.getStringExtra("description");
                Log.i("job", data.getStringExtra("job"));
                Log.i("deadline", data.getStringExtra("deadline"));
                ListViewItem item = new ListViewItem();
                item.setJob(job);
                item.setDeadline(deadline);
                item.setDetail(desc);
                myApp.task_list.add(item);
                adapter.notifyDataSetChanged();
            }
        }
        else if(requestCode == REQUEST_EDIT){
            if(resultCode == Activity.RESULT_OK){
                String job = data.getStringExtra("job");
                String deadline = data.getStringExtra("deadline");
                String desc = data.getStringExtra("description");
                int position = data.getIntExtra("position", 0);
                ListViewItem item = new ListViewItem();
                item.setJob(job);
                item.setDeadline(deadline);
                item.setDetail(desc);
                myApp.task_list.set(position, item);
                adapter.notifyDataSetChanged();
            }
        }
        else if(requestCode == REQUEST_MOVE){
            if(resultCode == Activity.RESULT_OK){
                int position = data.getIntExtra("position", 0);
                myApp.task_list.remove(position);
                task_view.clearChoices();
                adapter.notifyDataSetChanged();
            }
        }
    }
    public void onListBtnClick(View v, ListViewItem item){
        final int position = (int)v.getTag();
        int id = (int)v.getId();
        final ListViewItem onItem = item;
        Log.e("Log", "OnTask");
        Log.e("pos : ","" + position);
        Log.e("id : ", ""+id);
        switch(v.getId()){
            case R.id.edit_button:
                Log.e("on edit", "working");
                Intent intent = new Intent(Task.this, AddCard.class);
                intent.putExtra("job", onItem.getJob());
                intent.putExtra("deadline", onItem.getDeadline());
                intent.putExtra("detail", onItem.getDetail());
                intent.putExtra("position", position);
                intent.putExtra("request", 2);
                startActivityForResult(intent, REQUEST_EDIT);
                break;
            case R.id.delete_button:
                myApp.task_list.remove(position);
                task_view.clearChoices();
                adapter.notifyDataSetChanged();
                break;
            case R.id.Move_button:
                PopupMenu p = new PopupMenu(getApplicationContext(), v);
                getMenuInflater().inflate(R.menu.menu_task, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String s = item.getTitle().toString();
                        switch(s){
                            case "Doing":
                                Intent intent2 = new Intent(Task.this, Doing.class);
                                intent2.putExtra("job", onItem.getJob());
                                intent2.putExtra("deadline", onItem.getDeadline());
                                intent2.putExtra("detail", onItem.getDetail());
                                intent2.putExtra("position", position);
                                intent2.putExtra("request", REQUEST_MOVE);
                                startActivityForResult(intent2, REQUEST_MOVE);
                                break;
                            case "Done":
                                Intent intent3 = new Intent(Task.this, Done.class);
                                intent3.putExtra("job", onItem.getJob());
                                intent3.putExtra("deadline", onItem.getDeadline());
                                intent3.putExtra("detail", onItem.getDetail());
                                intent3.putExtra("position", position);
                                intent3.putExtra("request", REQUEST_MOVE);
                                startActivityForResult(intent3, REQUEST_MOVE);
                                break;
                        }

                        return false;
                    }
                });
                p.show();
                break;
        }

    }
}
