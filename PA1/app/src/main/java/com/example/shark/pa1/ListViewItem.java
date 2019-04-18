package com.example.shark.pa1;

public class ListViewItem {
    private String job_text;
    private String deadline_text;
    private String detail_text;

    public void setJob(String job){
        job_text = job;
    }
    public void setDeadline(String deadline){
        deadline_text = deadline;
    }
    public void setDetail(String detail){
        detail_text = detail;
    }

    public String getJob(){
        return this.job_text;
    }
    public String getDeadline(){
        return this.deadline_text;
    }
    public String getDetail(){
        return this.detail_text;
    }
}
