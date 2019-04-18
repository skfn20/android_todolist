package com.example.shark.pa1;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    public ArrayList<ListViewItem> task_list;
    public ArrayList<ListViewItem> doing_list;
    public ArrayList<ListViewItem> done_list;

    public void onCreate(){
        super.onCreate();
        task_list = new ArrayList<>();
        doing_list = new ArrayList<>();
        done_list = new ArrayList<>();
    }

    public void showData(){

    }
}
