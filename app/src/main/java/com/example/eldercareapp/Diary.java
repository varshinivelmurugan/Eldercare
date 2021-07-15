package com.example.eldercareapp;
/*-------------code snippet written by me----------------*/
public class Diary {
    private long id;
    private String title;
    private String content;
    private String date;
    private String time;
    private String user;
    Diary(){}
    Diary(String title, String content, String date, String time,String user){
        this.user = user;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }
    Diary(long id,String title, String content, String date, String time,String user) {
        this.id=id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.user=user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
